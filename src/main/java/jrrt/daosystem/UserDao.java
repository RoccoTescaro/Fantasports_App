package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

import jakarta.persistence.EntityManager;

import jrrt.entities.User;

public class UserDao implements Dao<User>
{
    private EntityManager entity_manager;

    @Override 
    public Optional<User> get(Long id) 
    {
        return Optional.ofNullable(entity_manager.find(User.class, id));
    }

    @Override
    public List<User> getAll() 
    {
        return entity_manager.createQuery("SELECT t FROM users t", User.class).getResultList();
    }

    @Override
    public void add(User user) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.persist(user);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void update(User user, String[] params) 
    {
        entity_manager.getTransaction().begin();
        user.setUsername(params[0]);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void delete(User user) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.remove(user);
        entity_manager.getTransaction().commit();
    }
}