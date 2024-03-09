package jrrt.gui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jrrt.daosystem.UserDao;
import jrrt.entities.User;
import jrrt.daosystem.LeagueDao;
import jrrt.entities.League;

@Controller
public class MainPage
{
    private final UserDao user_dao;
    private final LeagueDao league_dao;
    
    @Autowired
    public MainPage(UserDao user_dao, LeagueDao league_dao) 
    {
        this.user_dao = user_dao;
        this.league_dao = league_dao;
    }

    
}
