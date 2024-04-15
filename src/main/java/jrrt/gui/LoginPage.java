package jrrt.gui;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

@Controller
public class LoginPage 
{
    private final UserDao userDao;
    private final HttpSession session;

    @Autowired
    public LoginPage(UserDao userDao, HttpSession session) 
    {
        this.userDao = userDao;
        this.session = session;
    }

    @GetMapping("/")
    public String loginPage(Model model) 
    {
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) 
    {
        if (!userDao.getByName(user.getUsername()).isPresent())
        {
            userDao.save(user);
            model.addAttribute("info", "singup successful, please login");
            return "loginPage"; //dont use redirect, otherwise the model will be lost
        }
        else 
        {
            model.addAttribute("info", "username already taken");
            return "loginPage"; //dont use redirect, otherwise the model will be lost
        }
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) 
    {
        Optional<User> optUser = userDao.getByName(user.getUsername());
        if (!optUser.isPresent())
        {
            model.addAttribute("user", user);
            model.addAttribute("info", "user does not exist, signup first");
            return "loginPage"; //dont use redirect, otherwise the model will be lost
        }
        
        User actUser = optUser.get();
        if (actUser.getPassword().equals(user.getPassword()))
        {
            session.setAttribute("user", actUser);
            return "redirect:/main";
        }
        else 
        {
            model.addAttribute("user", user);
            model.addAttribute("info", "invalid credentials");
            return "loginPage"; //dont use redirect, otherwise the model will be lost
        }
    }

}