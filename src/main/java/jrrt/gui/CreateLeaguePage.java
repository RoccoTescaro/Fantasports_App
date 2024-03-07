package jrrt.gui;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.daosystem.LeagueDao;
import jrrt.entities.League;
import java.util.Optional; // Add this import statement

@Controller
public class CreateLeaguePage
{
    private final LeagueDao league_dao;
    
    @Autowired
    public CreateLeaguePage(LeagueDao league_dao) 
    {
        this.league_dao = league_dao;
    }
    
    @GetMapping("/createLeague")
    public String showAddLeaguePage(Model model) 
    {
        // add any attributes to the model you want to use in the view
        return "create_league_page";
    }
}

