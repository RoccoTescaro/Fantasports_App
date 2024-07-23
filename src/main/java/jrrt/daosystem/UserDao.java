package jrrt.daosystem;

import java.util.HashSet;
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
    private final UserRepo userRepo;
    
    @Autowired
    public UserDao(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public UserDao save(User user)
    {
        System.out.println("UserDao.save(" + user + ")"); //DEBUG
        userRepo.save(user);
        return this;
    }

    @Override
    public UserDao delete(User user)
    {
        System.out.println("UserDao.delete(" + user + ")"); //DEBUG
        userRepo.delete(user);
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
        Set<User> users = new HashSet<User>();
        for (User user : userRepo.findAll())
            users.add(user);

        return users;
    }

    @Override
    public Optional<User> get(Long id)
    {
        return userRepo.findById(id);
    }

    public Optional<User> getByName(String name)
    {
        return userRepo.getByName(name);
    }
}