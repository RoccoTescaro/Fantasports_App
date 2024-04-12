package jrrt.gui;

import java.time.LocalDate;
import java.util.List;
import java.util.Set; 
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String mainPage(Model model, HttpSession session) 
    {
        User existingUser = (User) session.getAttribute("user");
        if (existingUser == null) 
            return "login_page"; //should send an error message

        Optional<User> user_opt = user_dao.getById(existingUser.getId());
        
        if (user_opt.isPresent()) 
        {
            
            User user = user_opt.get();
            model.addAttribute("user", user);
            model.addAttribute("alert_leagues", user.getAlertLeagues());
            model.addAttribute("other_leagues", user.getOtherLeagues());
          
            //fix this relational problem user attend leagues
        }

        return "main_page";
    }

    @GetMapping("/league/{id}")
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

    @PostMapping("/joinLeague")
    public String joinLeague(@RequestParam("leagueId") Long leagueId, HttpSession session, Model model) {
        // Get the user from the session
        User user = (User) session.getAttribute("user");

        // Fetch the league using the provided ID
        Optional<League> leagueOpt = league_dao.get(leagueId);

        if (leagueOpt.isPresent()) {
            League league = leagueOpt.get();

            // Check if the league is full
            if (league.getParticipants().size() >= league.getNumberParticipants()) {
                model.addAttribute("error", "The league is full.");
                return "main_page";
            }

            // Add the user to the league
            league.addParticipant(user);
            league_dao.save(league);

            model.addAttribute("message", "You have successfully joined the league.");
        } else {
            model.addAttribute("error", "The league does not exist.");
        }

        return "main_page";
    }//fix this, gives error

}

