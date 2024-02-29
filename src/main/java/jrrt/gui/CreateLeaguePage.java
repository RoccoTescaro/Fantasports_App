package jrrt.gui;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.daosystem.LeagueDao;
import jrrt.entities.League;
import java.util.Optional; // Add this import statement

@Controller
public class CreateLeaguePage
{
    private final LeagueDao league_dao;
    
    @Autowired
    public CreateLeaguePage(LeagueDao league_dao) 
    {
        this.league_dao = league_dao;
    }
    

    /*@GetMapping("/login")
    public String login(Model model) 
    {
        model.addAttribute("user", new User());
        return "login_page";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, Model model) 
    {
        model.addAttribute("user", user);        
        if (!user_dao.getByName(user.getUsername()).isPresent())
        {
            user_dao.save(user);
            model.addAttribute("info", "singup successful, please login");
            return "login_page";
        }
        else 
        {
            model.addAttribute("info", "username already taken");
            return "login_page";
        }
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) 
    {
        Optional<User> optionalUser = user_dao.getByName(user.getUsername());
        if (!optionalUser.isPresent())
        {
            model.addAttribute("user", user);
            model.addAttribute("info", "user does not exist, signup first");
            return "login_page";
        }
        
        User existingUser = optionalUser.get();
        if (existingUser.getPassword().equals(user.getPassword()))
        {
            return "main_page";
        }
        else 
        {
            model.addAttribute("user", user);
            model.addAttribute("info", "invalid credentials");
            System.out.println(existingUser);
            return "login_page";
        }
    }*/
}

