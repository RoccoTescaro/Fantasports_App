package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jrrt.entities.User;
import jrrt.entities.League;
import jrrt.repositories.UserRepo;
import jrrt.entities.League;

@Service
public class UserDao implements Dao<User> 
{   
    private final UserRepo user_repo;
    
    @Autowired
    public UserDao(UserRepo user_repo)
    {
        this.user_repo = user_repo;
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

    public Optional<User> getByName(String name)
    {
        return user_repo.getByName(name);
    }

    public List<League> getUserLeagues(String username)
    {
        return user_repo.getUserLeagues(username);
    }
}