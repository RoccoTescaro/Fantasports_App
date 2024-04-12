package jrrt.daosystem;

import java.util.Optional;
import java.util.Set;

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
    public Set<User> getAll()
    {
        Set<User> users = (Set<User>) user_repo.findAll();
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

    public Set<League> getUserLeagues(String username)
    {
        return user_repo.getUserLeagues(username);
    }

    //find by id
    public Optional<User> getById(Long id)
    {
        return user_repo.findById(id);
    }

}