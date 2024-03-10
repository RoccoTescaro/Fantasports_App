package jrrt.gui;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

@Controller
public class LoginPage {
    private final UserDao user_dao;
    private final LeagueDao league_dao;

    @Autowired
    public LoginPage(UserDao user_dao, LeagueDao league_dao) {
        this.user_dao = user_dao;
        this.league_dao = league_dao;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login_page";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, Model model) {
        System.out.println(user);
        if (!user_dao.getByName(user.getUsername()).isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user_dao.save(user);
            model.addAttribute("info", "signup successful, please login");
            return "login_page";
        } else {
            model.addAttribute("info", "username already taken");
            return "login_page";
        }
    }

    @PostMapping("/main")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        Optional<User> optionalUser = user_dao.getByName(user.getUsername());
        if (!optionalUser.isPresent()) {
            model.addAttribute("info", "user does not exist, signup first");
            return "login_page";
        }

        User existingUser = optionalUser.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
            user_dao.save(existingUser);

            Set<League> leagues = existingUser.getAttendedLeagues();
            LocalDate today = LocalDate.now();

            //#TODO - fix this with the correct system time
            //remove from database finished leagues
            Set<League> alert_leagues = leagues.stream().filter(l -> l.getStartDate() != null && !l.getStartDate().isAfter(today)).collect(Collectors.toSet());
            Set<League> other_leagues = leagues.stream().filter(l -> l.getStartDate() != null && l.getStartDate().isAfter(today)).collect(Collectors.toSet());

            model.addAttribute("alert_leagues", alert_leagues);
            model.addAttribute("other_leagues", other_leagues);

            return "main_page";
        } else {
            model.addAttribute("info", "invalid credentials");
            System.out.println(existingUser);
            return "login_page";
        }
    }
}