package jrrt.gui;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.TeamDao;
import jrrt.entities.User;
import jrrt.entities.League;
import jrrt.entities.Team;

@Controller
public class CreateNewLeaguePage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final TeamDao teamDao;
    private final HttpSession session;

    @Autowired
    public CreateNewLeaguePage(UserDao userDao, LeagueDao leagueDao, TeamDao teamDao, HttpSession session)
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;       
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

}
