
package jrrt.gui;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;
import jrrt.daosystem.LeagueDao;

@Controller
public class MainPage {

    private final UserDao user_dao;
    private final LeagueDao league_dao;

    @Autowired
    public MainPage(UserDao user_dao, LeagueDao league_dao) 
    {
        this.user_dao = user_dao;
        this.league_dao = league_dao;
    }

    @GetMapping("/main")
    public String main(@ModelAttribute User user, Model model) 
    {
        // Add any attributes to the model that you need for the main page
        String username = user.getUsername();
        List<League> leagues = user_dao.findAttendedLeaguesByName(username);
        leagues.forEach(league -> league.getName());
        model.addAttribute("attended_leagues", leagues);
        return "main_page";
    }

    @PostMapping("/league")
    public String createLeagueSubmit( Model model, Principal principal) 
    {
        return "create_league_page";
    }
    
    @GetMapping("/league")
    public String createLeague(Model model, Principal principal) 
    {
        return "create_league_page";
    }
}
