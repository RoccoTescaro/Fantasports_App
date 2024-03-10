package jrrt.gui;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

import java.security.Principal;
import java.util.Optional; // Add this import statement


@Controller
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
    public String showCreateLeaguePage(@ModelAttribute User user, Model model, Principal principal) 
    {
        System.out.println("User: " + principal.getName());  
        model.addAttribute("league", new League());
        return "create_league_page";
    }

    @PostMapping("/createNewLeague")
    public String createLeague(@ModelAttribute League league, @ModelAttribute User user, Model model, Principal principal) 
    {
        league.setCreator(user);
        league_dao.save(league);

        System.out.println("User: " + principal.getName());  
        return "main_page";        
    }
}

