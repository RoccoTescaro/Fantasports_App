package jrrt.gui;

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

        //should check for leagues with the same name
        leagueDao.save(league);
        Team team = new Team();
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);
        //userDao.save(user); //check if needed for persistance
        return "redirect:/main";
    }

}


/*package jrrt.gui;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

import java.security.Principal;
import java.util.Optional; // Add this import statement
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttribute; // Add this import statement


@Controller
public class CreateLeaguePage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    
    @Autowired
    public CreateLeaguePage(LeagueDao leagueDao, UserDao userDao) 
    {
        this.leagueDao = leagueDao;
        this.userDao = userDao;
    }
    
    @GetMapping("/createLeague")
    public String createLeaguePage(Model model, HttpSession session) 
    {
        User user = (User) session.getAttribute("user");
        if (user == null) 
            return "login_page"; //should send an error message
            
        model.addAttribute("league", new League());
        model.addAttribute("user", user);
        return "create_league_page";
    }

    @PostMapping("/createNewLeague")
    public String createLeague(@ModelAttribute League league, @ModelAttribute("user") User user )
    {
        if (user == null) return "create_league_page";

        league.setCreator(user);
        league_dao.save(league);

        User user_tmp = user_dao.getById(user.getId()).get();
        League league_tmp = league_dao.get(league.getId()).get();

        if ( user_tmp == null || league_tmp == null )
            System.out.println("Error: user or league not found\n");

        league_tmp.setCreator(user_tmp);
        //user_tmp.getCreatedLeagues().add(league_tmp);
        user_tmp.getAttendedLeagues().add(league_tmp);

        league_dao.save(league);
        user_dao.save(user);

        league = league_dao.get(league.getId()).get();
        user = user_dao.getById(user.getId()).get();

        System.out.println("league participants: \n");
        for (User u : league_tmp.getParticipants())
            System.out.println(u.getUsername() + "\n");

        System.out.println("user attended leagues: \n");
        for (League l : user_tmp.getAttendedLeagues())
            System.out.println(l.getName() + "\n");

        return "redirect:/main";
    }
}*/

