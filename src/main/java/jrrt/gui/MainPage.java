package jrrt.gui;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jrrt.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import jrrt.entities.User;
import jrrt.entities.League;
import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.TeamDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainPage {
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final HttpSession session;
    private final TeamDao teamDao;


    @Autowired
    public MainPage(UserDao userDao, LeagueDao leagueDao, TeamDao teamDao, HttpSession session) 
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.session = session;
        this.teamDao = teamDao;
    }

    @GetMapping("/main")
    public String mainPage(Model model) 
    {
        User actUser = (User) session.getAttribute("user");
        if (actUser == null)
            return "redirect:/"; //should send an error message
        Optional<User> optUser = userDao.get(actUser.getId());
        if (!optUser.isPresent())
            return "redirect:/"; //should send an error message

        User user = optUser.get();
        model.addAttribute("user", user);
        session.setAttribute("user", user);

        Set<League> leagues = leagueDao.getUserAttendedLeagues(user.getId());
        List<League> sortedLeagues = leagues.stream().sorted((l1, l2) -> l1.getStartDate().compareTo(l2.getStartDate())).collect(Collectors.toList());

        //LocalDate twoDaysFromNow = LocalDate.now().plusDays(2);

        //Set<League> leagues = attendedLeagues.stream().filter(league -> !league.getStartDate().equals(twoDaysFromNow) && !league.getStartDate().isBefore(twoDaysFromNow)).collect(Collectors.toSet());
        model.addAttribute("leagues", sortedLeagues);

        //Set<League> alertLeagues = attendedLeagues.stream().filter(league -> league.getStartDate().equals(twoDaysFromNow) || league.getStartDate().isBefore(twoDaysFromNow)).collect(Collectors.toSet());
        //model.addAttribute("alertLeagues", alertLeagues);

        return "mainPage";
    }

    @GetMapping("/newLeague")
    public String newLeague(Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/"; //should send an error message

        model.addAttribute("user", user);
        model.addAttribute("league", new League());
        return "createNewLeaguePage";
    }


    @PostMapping("/joinLeague")
    public String joinLeague(@RequestParam("leagueId") Long leagueId, Model model) 
    {
        User user = (User) session.getAttribute("user");
        Optional<League> leagueOpt = leagueDao.get(leagueId);

        if (!leagueOpt.isPresent()) {
            model.addAttribute("error", "The league does not exist.");
            return "redirect:/main"; //should send an error message
        }
        League league = leagueOpt.get();
        if (leagueDao.getTeams(league.getId()).size() >= league.getNParticipants()) {
            model.addAttribute("error", "The league is already full.");
            return "redirect:/main";
        }

        Set<Team> teams = leagueDao.getTeams(league.getId());
        for (Team t : teams)
            if (t.getOwner().getId() == user.getId() ) {
                model.addAttribute("error", "You are already in this league.");
                return "redirect:/main";
            }
        Team team = new Team();
        team.setOwner(user);
        team.setLeague(league);
        leagueDao.save(league);
        teamDao.save(team);

        return "redirect:/main";
    }
}


    /*@GetMapping("/league/{id}")
    public String showLeaguePage(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<League> league_opt = league_dao.get(id);
        if (league_opt.isPresent()) {
            League league = league_opt.get();
            model.addAttribute("league", league);

            User user = (User) session.getAttribute("user");
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

    @PostMapping("/joinLeague")
    public String joinLeague(@RequestParam("leagueId") Long leagueId, HttpSession session, Model model) {
        // Get the user from the session
        User user = (User) session.getAttribute("user");

        // Fetch the league using the provided ID
        Optional<League> leagueOpt = league_dao.get(leagueId);

        if (leagueOpt.isPresent()) {
            League league = leagueOpt.get();

            // Check if the league is full
            if (league.getParticipants().size() >= league.getNumberParticipants()) {
                model.addAttribute("error", "The league is full.");
                return "main_page";
            }

            // Add the user to the league
            league.addParticipant(user);
            league_dao.save(league);

            model.addAttribute("message", "You have successfully joined the league.");
        } else {
            model.addAttribute("error", "The league does not exist.");
        }

        return "main_page";
    }//fix this, gives error
    */



