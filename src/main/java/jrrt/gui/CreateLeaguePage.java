package jrrt.gui;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;

import java.security.Principal;
import java.util.Optional; // Add this import statement


@Controller
public class CreateLeaguePage
{
    private final LeagueDao league_dao;
    private final UserDao user_dao;
    
    @Autowired
    public CreateLeaguePage(LeagueDao league_dao, UserDao user_dao) 
    {
        this.league_dao = league_dao;
        this.user_dao = user_dao;
    }
    
    @GetMapping("/createLeague")
     public String showCreateLeaguePage(@ModelAttribute User user, Model model) {
                model.addAttribute("league", new League());
               // model.addAttribute("user", user);
        return "create_league_page";
    }

    @PostMapping("/createNewLeague")
    public String createLeague(@ModelAttribute League league, Principal principal) {
        Optional<User> userOptional = user_dao.getByName(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            league.setCreator(user); 
            league_dao.save(league);
            
            return "main_page";//"redirect:/main" But this is not working
        } else 
        {
            return "create_league_page";
        }
    }


}

