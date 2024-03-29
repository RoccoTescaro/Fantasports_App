package jrrt.gui;

import java.time.LocalDate;
import java.util.List;
import java.util.Set; 
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;


import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
    User existingUser = (User) session.getAttribute("user");
    if (existingUser != null) {
        Optional<User> userOptional = user_dao.getById(existingUser.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("alert_leagues", user.getAlertLeagues());
            model.addAttribute("other_leagues", user.getOtherLeagues());
            //fix this relational problem user attend leagues
        }
    return "main_page";
        }
    return "login_page";
    }
}
