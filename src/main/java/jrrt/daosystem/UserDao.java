package jrrt.core.daosystem;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jrrt.core.repositories.UserRepo;
import jrrt.entities.User;

@Controller
public class UserDao implements Dao<User> 
{   
    private final UserRepo user_repo;
    
    @Autowired
    public UserDao(UserRepo user_repo)
    {
        this.user_repo = user_repo;
    }
    
    @GetMapping("/login")
    public String login(Model model) 
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) 
    {
        model.addAttribute("user", user);
        user_repo.save(user);
        return "result";
    }

    @Override
    public UserDao save(User user)
    {
        user_repo.save(user);
        return this;
    }

    @Override
    public UserDao delete(User user)
    {
        user_repo.delete(user);
        return this;
    }

    @Override
    public UserDao update(User user, String[] params)
    {
        //user_repo.update(user, params);
        return this;
    }

    @Override
    public List<User> getAll()
    {
        List<User> users = (List<User>) user_repo.findAll();
        return users;
    }

    @Override
    public Optional<User> get(Long id)
    {
        return user_repo.findById(id);
    }

    //public Optional<User> getByName(String name)
    //{
    //    return user_repo.getByName(name);
    //}

}