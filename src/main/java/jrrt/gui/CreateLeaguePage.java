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
    public String createLeague(@ModelAttribute League league, @ModelAttribute("user") User user ){
        
        //Optional<User> userOptional = user_dao.getByName(principal.getName());
        //if (userOptional.isPresent()) {
        if (user != null) {
           // User user = userOptional.get();
            league.setCreator(user); 

            league.addParticipant(user);
            
            league_dao.save(league);
            
            
            return "redirect:/main";
        } else 
        {
            return "create_league_page";
        }
    }


}

