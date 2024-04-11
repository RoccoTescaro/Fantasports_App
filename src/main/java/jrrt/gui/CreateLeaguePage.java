package jrrt.gui;

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
@SessionAttributes("user")
public class CreateLeaguePage
{
    private final LeagueDao league_dao;
    private final UserDao user_dao;
    
    @Autowired
    public CreateLeaguePage(LeagueDao league_dao, UserDao user_dao) 
    {
        this.league_dao = league_dao;
        this.user_dao = user_dao;
    }
    
    @GetMapping("/createLeague")
    public String showCreateLeaguePage(Model model, @SessionAttribute("user") User user) {
       // User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("User is null");
        }
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

        user_tmp.createLeague(league_tmp);
        league_tmp.setCreator(user_tmp);
        league_tmp.addParticipant(user_tmp);

        System.out.println("league participants: \n");
        for (User u : league_tmp.getParticipants())
            System.out.println(u.getUsername() + "\n");

        System.out.println("user attended leagues: \n");
        for (League l : user_tmp.getAttendedLeagues())
            System.out.println(l.getName() + "\n");

        user_dao.save(user);
        league_dao.save(league);

        return "redirect:/main";
    }
}

