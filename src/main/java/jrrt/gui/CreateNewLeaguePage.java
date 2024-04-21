package jrrt.gui;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.TeamDao;
import jrrt.daosystem.PlayerDao;
import jrrt.entities.User;
import jrrt.entities.League;
import jrrt.entities.Team;
import jrrt.entities.Player;

@Controller
public class CreateNewLeaguePage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final TeamDao teamDao;
    private final PlayerDao playerDao;
    private final HttpSession session;

    @Autowired
    public CreateNewLeaguePage(UserDao userDao, LeagueDao leagueDao, TeamDao teamDao, PlayerDao playerDao, HttpSession session)
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;       
        this.playerDao = playerDao;
        this.session = session; 
    }

    @PostMapping("/createNewLeague")
    public String createNewLeague(@ModelAttribute League league, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/"; //should send an error message

        Set<League> leaguesWithSameName = leagueDao.getByName(league.getName());
        for (League l : leaguesWithSameName)
            if (l.getCreator().getId() == user.getId())
                return "redirect:/modifyLeague"; //should send to modify league page

        //should check for leagues with the same name
        league.setCreator(user);
        leagueDao.save(league);
        Team team = new Team();
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);
        //userDao.save(user); //check if needed for persistance
        return "redirect:/main";
    }

    @PostMapping("/addPlayer/{playerName}")
    public String addPlayerToLeaguePool(@PathVariable String playerName, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/"; //should send an error message

        System.out.println("addPlayerToLeaguePool: " + playerName);
        League league = (League) session.getAttribute("league");
        if (league == null)
            return "redirect:/"; //should send an error message

        //check if player already exists and if he is in the league
        Player player = new Player();
        player.setName(playerName);
        player.addLeague(league);
        playerDao.save(player);

        league.cleanPlayers();
        league.addPlayer(player);
        leagueDao.save(league);

        return "redirect:/newLeague";
    }

}
