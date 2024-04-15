package jrrt.gui;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import jrrt.entities.User;
import jrrt.entities.League;
import jrrt.daosystem.UserDao;
import jrrt.daosystem.LeagueDao;


@Controller
public class MainPage
{
    private final UserDao userDao;
    private final LeagueDao leagueDao;
    private final HttpSession session;

    
    @Autowired
    public MainPage(UserDao userDao, LeagueDao leagueDao, HttpSession session) 
    {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.session = session;
    }

    @GetMapping("/main")
    public String mainPage(Model model) 
    {
        User actUser = (User) session.getAttribute("user");
        if (actUser == null)
            return "redirect:/"; //should send an error message
        Optional<User> optUser = userDao.get(actUser.getId());
        if (!optUser.isPresent())
            return "redirect:/"; //should send an error message

        User user = optUser.get();
        model.addAttribute("user", user);
        session.setAttribute("user", user);

        Set<League> leagues = leagueDao.getUserAttendedLeagues(user.getId());
        System.out.println("leagues: " + leagues);
        
        //model.addAttribute("alert_leagues", user.getAlertLeagues());
        //model.addAttribute("other_leagues", user.getOtherLeagues());

        return "mainPage";
    }

    @GetMapping("/newLeague")
    public String newLeague(Model model) 
    {
        User user = (User) session.getAttribute("user");
        if (user == null) 
            return "redirect:/"; //should send an error message
            
        model.addAttribute("user", user);
        model.addAttribute("league", new League());
        return "createNewLeaguePage";
    }

    /*@GetMapping("/league/{id}")
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
*/

}
