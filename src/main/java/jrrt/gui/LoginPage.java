package jrrt.gui;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.daosystem.UserDao;
import jrrt.entities.User;

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
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) 
    {
        model.addAttribute("user", user);
        user_dao.save(user);
        return "result";
    }
}