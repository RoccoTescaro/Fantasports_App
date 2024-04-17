package jrrt.gui;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.entities.League;
import jrrt.entities.User;

@Controller
public class LeagueDetailsPage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final HttpSession session;

    @Autowired
    public LeagueDetailsPage(UserDao userDao, LeagueDao leagueDao, HttpSession session)
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.session = session;
    }

    @GetMapping("/leagueDetails/{id}")
    public String leagueDetails(@PathVariable Long id, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null) 
            return "redirect:/"; //should send an error message
            
        model.addAttribute("user", user);

        Optional<League> leagueOpt = leagueDao.get(id);
        if (!leagueOpt.isPresent()) 
            return "redirect:/main"; //should send an error
        
        League league = leagueOpt.get();
        model.addAttribute("league", league);
        
        return "leagueDetailsPage";
    }

}


/*package jrrt.gui;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@SessionAttributes({"user", "league"})
public class LeaguePage {

    private final LeagueDao league_dao;
    private final UserDao user_dao;

    @Autowired
    public LeaguePage(LeagueDao league_dao, UserDao user_dao)
    {
        this.league_dao = league_dao;
        this.user_dao = user_dao;
    }



    @GetMapping("/main/league/{id}")
    public String showLeaguePage(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<League> league_opt = league_dao.get(id);
        if (league_opt.isPresent()) {
            League league = league_opt.get();
            model.addAttribute("league", league);

            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                return "main_page"; // or wherever you want to redirect if the user isn't logged in
            }

            return "league_page";
        } else {
            return "main_page"; // or wherever you want to redirect if the league is not found
        }
    }



    private League fetchLeagueById(Long id) {
        // This is just a placeholder, replace it with your actual method to fetch the league
        Optional<League> league_tmp = league_dao.get(id);
        return league_tmp.orElse(null);
    }
}*/