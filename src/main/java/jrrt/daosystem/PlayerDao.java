package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

import jakarta.persistence.EntityManager;

import jrrt.entities.Player;

public class PlayerDao implements Dao<Player>
{
    private EntityManager entity_manager;

    @Override 
    public Optional<Player> get(Long id) 
    {
        return Optional.ofNullable(entity_manager.find(Player.class, id));
    }

    @Override
    public List<Player> getAll() 
    {
        return entity_manager.createQuery("SELECT t FROM players t", Player.class).getResultList();
    }

    @Override
    public void add(Player player) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.persist(player);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void update(Player player, String[] params) 
    {
        entity_manager.getTransaction().begin();
        player.setName(params[0]);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void delete(Player player) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.remove(player);
        entity_manager.getTransaction().commit();
    }
}