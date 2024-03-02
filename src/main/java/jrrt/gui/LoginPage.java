package jrrt.gui;

import java.util.Optional; 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.daosystem.UserDao;
import jrrt.entities.User;
import jrrt.entities.League;

@Controller
public class LoginPage
{
    private final UserDao user_dao;
    
    @Autowired
    public LoginPage(UserDao user_dao) 
    {
        this.user_dao = user_dao;
    }

    @GetMapping("/login")
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

    @PostMapping("/main")
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
            League league = new League(); //just for testing #TODO remove this
            user.addAttendedLeague(league);
            //league_dao.save(league);
        
            List<League> leagues = user_dao.getUserLeagues(user.getUsername());
            model.addAttribute("leagues", leagues);
            System.out.println("leagues: " + leagues);

            return "main_page";
        }
        else 
        {
            model.addAttribute("user", user);
            model.addAttribute("info", "invalid credentials");
            System.out.println(existingUser);
            return "login_page";
        }
    }
}