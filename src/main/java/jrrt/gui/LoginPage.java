package jrrt.gui;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional; 
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.LocalDate;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;
import jrrt.entities.User;
import jrrt.entities.League;

@Controller
public class LoginPage
{
    private final UserDao user_dao;
    private final LeagueDao league_dao;
    
    @Autowired
    public LoginPage(UserDao user_dao, LeagueDao league_dao) 
    {
        this.user_dao = user_dao;
        this.league_dao = league_dao;
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
            League futureLeague = new League();
            futureLeague.setName("Future League");
            futureLeague.setStartDate(LocalDate.now().plusDays(3));
            league_dao.save(futureLeague);
            existingUser.getAttendedLeagues().add(futureLeague);
            futureLeague.getParticipants().add(existingUser);

            // Create a league with a start date before today
            League pastLeague = new League();
            pastLeague.setName("Past League");
            pastLeague.setStartDate(LocalDate.now().minusDays(1));
            league_dao.save(pastLeague);
            existingUser.getAttendedLeagues().add(pastLeague);
            pastLeague.getParticipants().add(existingUser);

            user_dao.save(existingUser);
            /*if (existingUser != null && league != null)
            {
                league_dao.save(league);
                existingUser.getAttendedLeagues().add(league);
                league.getParticipants().add(existingUser);
                user_dao.save(existingUser);
            }*/

            //league_dao.save(league);
            Set<League> leagues = existingUser.getAttendedLeagues();
            LocalDate today = LocalDate.now();
            //model.addAttribute("leagues", leagues);
            //To do - fix this with the correct system time
            Set<League> alert_leagues = leagues.stream().filter(l -> l.getStartDate() != null && !l.getStartDate().isAfter(today)).collect(Collectors.toSet());
            Set<League> other_leagues = leagues.stream().filter(l -> l.getStartDate() != null && l.getStartDate().isAfter(today)).collect(Collectors.toSet());

            model.addAttribute("alert_leagues", alert_leagues);
            model.addAttribute("other_leagues", other_leagues);
            //System.out.println("leagues: " + leagues);


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

    @PostMapping("/creatNewLeague")
    public String createNewLeague(@ModelAttribute League league, Model model) 
    {
        model.addAttribute("league", league);
        league_dao.save(league);
        return "create_league_page";
    }
}