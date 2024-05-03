package jrrt.gui;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;

import jrrt.entities.Player;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

import jrrt.daosystem.PlayerDao;
import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.TeamDao;
import jrrt.entities.League;
import jrrt.entities.User;
import jrrt.entities.Team;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeagueDetailsPage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final TeamDao teamDao;
    private final PlayerDao playerDao;
    private final HttpSession session;

    @Autowired
    public LeagueDetailsPage(UserDao userDao, LeagueDao leagueDao, TeamDao teamDao, PlayerDao playerDao, HttpSession session)
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;
        this.playerDao = playerDao;
        this.session = session;
    }

    @GetMapping("/leagueDetails/{id}")
    public String leagueDetails(@PathVariable Long id, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null) 
            return "redirect:/"; //should send an error message
            
        model.addAttribute("user", user);

        Optional<League> leagueOpt = leagueDao.get(id);
        if (!leagueOpt.isPresent()) 
            return "redirect:/main"; //should send an error
        
        League league = leagueOpt.get();
        model.addAttribute("league", league);
        session.setAttribute("league", league); //session must save the current league shown so that modify button can actually see it
    
        model.addAttribute("modifyButton", false);
        if(league.getCreator().getId() == user.getId())
            model.addAttribute("modifyButton", true);

        LocalDate date = LocalDate.now();
        model.addAttribute("ranking", false);
        if(!league.getStartDate().isAfter(date))
        {
            //show ranking
            model.addAttribute("ranking", true);
            Set<Team> teams = leagueDao.getTeams(league.getId());
            List<Team> sortedTeams = teams.stream().sorted((t1, t2) -> t2.getPoints() - t1.getPoints()).collect(Collectors.toList());
            model.addAttribute("teams", sortedTeams);
        }

        //Rocco guardare qui se ti va bene
        Set<Player> userPlayers = new HashSet<>();
        Set<Team> leagueTeams = leagueDao.getTeams(league.getId());
        for (Team t : leagueTeams)
        {
            if (t.getOwner().getId().equals(user.getId()) )
            {
                Set<Player> teamPlayers = t.getPool();
                userPlayers.addAll(teamPlayers);
            }
        }
        model.addAttribute("userPlayers", userPlayers);

        return "leagueDetailsPage";
    }

    @GetMapping("/modifyLeague")
    public String modifyLeague(Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null) 
            return "redirect:/"; //should send an error message
            
        model.addAttribute("user", user);

        League league = (League) session.getAttribute("league");
        if (league == null)
            return "redirect:/main"; //should send an error message

        model.addAttribute("league", league);
        Set<Team> teams = leagueDao.getTeams(league.getId());
        model.addAttribute("teams", teams);

        return "modifyLeaguePage";
    }

    @GetMapping("/removeTeam/{id}")
    public String removeTeam(@PathVariable Long id, Model model)
    {
        Optional<Team> teamOpt = teamDao.get(id);
        if (!teamOpt.isPresent()) 
            return "redirect:/main"; //should send an error message
        
        Team team = teamOpt.get();
        League league = (League) session.getAttribute("league");
        if (league == null)
            return "redirect:/main"; //should send an error message

        if (team.getOwner().getId() == ((User) session.getAttribute("user")).getId())
        {
            Set<Team> teams = leagueDao.getTeams(league.getId());
            User newCreator = null;
            for (Team t : teams)
            {
                if (t.getId() != team.getId())
                {
                    newCreator = t.getOwner();
                    break;
                }
            }
            if (newCreator != null)
                league.setCreator(newCreator);
            leagueDao.save(league);
            teamDao.delete(team);
            if (leagueDao.getTeams(league.getId()).isEmpty())
                leagueDao.delete(league);
            return "redirect:/main";
        }

        teamDao.delete(team);
        
        if (leagueDao.getTeams(league.getId()).isEmpty())
        {
            leagueDao.delete(league);
            return "redirect:/main";
        }

        return "redirect:/modifyLeague";
    }

    @GetMapping("/addPlayer/{userId}/{teamId}")
    public String addPlayer(@PathVariable Long userId,@PathVariable Long teamId, Model model)
    {
        Optional<User> userOpt = userDao.get(userId);
        if (!userOpt.isPresent())
            return "redirect:/main";

        User user = userOpt.get();
        model.addAttribute("user", user);

        Optional<Team> teamOpt = teamDao.get(teamId);
        if (!teamOpt.isPresent())
            return "redirect:/main";

        Team team = teamOpt.get();
        model.addAttribute("team", team);

        League league = (League) session.getAttribute("league");
        if (league == null)
            return "redirect:/main";

        Set<Player> players = leagueDao.getPlayers(league.getId());
        model.addAttribute("players", players);

        return "addPlayersPage";
    }

    @PostMapping("/addPlayersToTeam/{teamId}")
    public String addPlayersToTeam(@PathVariable Long teamId, @RequestParam("playerIds") List<Long> playerIds)
    {
        Optional<Team> teamOpt = teamDao.get(teamId);
        if (!teamOpt.isPresent()) {
            return "redirect:/main";
        }
        Team team = teamOpt.get();
        for (Long playerId : playerIds) {
            Optional<Player> playerOpt = playerDao.get(playerId);
            if (playerOpt.isPresent()) {
                Player player = playerOpt.get();
                team.getPool().add(player);
                teamDao.save(team);
            }
        }

        return "redirect:/leagueDetails/" + team.getLeague().getId();
    }
// TO FIX save in the pool

}


/*package jrrt.gui;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes({"user", "league"})
public class LeaguePage {

    private final LeagueDao league_dao;
    private final UserDao user_dao;

    @Autowired
    public LeaguePage(LeagueDao league_dao, UserDao user_dao)
    {
        this.league_dao = league_dao;
        this.user_dao = user_dao;
    }



    @GetMapping("/main/league/{id}")
    public String showLeaguePage(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<League> league_opt = league_dao.get(id);
        if (league_opt.isPresent()) {
            League league = league_opt.get();
            model.addAttribute("league", league);

            User user = (User) session.getAttribute("user");

            // Fetch the participants and their respective points or rankings
            Set<User> participants = league.getParticipants(); // Assuming getParticipants() method exists in League class
            // Print each participant's name
            for (User participant : participants) {
                System.out.println(participant.getUsername()); // Fix this line beacuse participants is empty
            }
            model.addAttribute("participants", participants);

            if (user != null) {
                model.addAttribute("user", user);
            } else {
                return "main_page"; // or wherever you want to redirect if the user isn't logged in
            }

            return "league_page";
        } else {
            return "main_page"; // or wherever you want to redirect if the league is not found
        }
    }



    private League fetchLeagueById(Long id) {
        // This is just a placeholder, replace it with your actual method to fetch the league
        Optional<League> league_tmp = league_dao.get(id);
        return league_tmp.orElse(null);
    }
}*/