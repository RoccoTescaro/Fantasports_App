package jrrt.repositories;
/*package jrrt.repositories;

import jakarta.persistence.EntityManager;

import jrrt.daosystem.UserDao;

public class PlayerRepo implements UserDao 
{
    private EntityManager entity_manager = new EntityManager();

    public void add(String username, String password) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.persist(new User(username, password));
        entity_manager.getTransaction().commit();
    }

    public void update(String username, String password) 
    {
        entity_manager.getTransaction().begin();
        User user = entity_manager.find(User.class, username);
        user.setPassword(password);
        entity_manager.getTransaction().commit();
    }

    public void delete(String username) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.remove(entity_manager.find(User.class, username));
        entity_manager.getTransaction().commit();
    }

    public Optional<User> findByUsername(String username) 
    {
        return Optional.ofNullable(entity_manager.find(User.class, username));
    }

    public List<User> getAll() 
    {
        return entity_manager.createQuery("SELECT t FROM users t", User.class).getResultList();
    }
}*/