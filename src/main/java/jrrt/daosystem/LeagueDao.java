package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

import jakarta.persistence.EntityManager;

import jrrt.entities.League;

public class LeagueDao implements Dao<League>
{
    private EntityManager entity_manager;

    @Override 
    public Optional<League> get(Long id) 
    {
        return Optional.ofNullable(entity_manager.find(League.class, id));
    }

    @Override
    public List<League> getAll() 
    {
        return entity_manager.createQuery("SELECT t FROM leagues t", League.class).getResultList();
    }

    @Override
    public void add(League league) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.persist(league);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void update(League league, String[] params) 
    {
        entity_manager.getTransaction().begin();
        league.setName(params[0]);
        entity_manager.getTransaction().commit();
    }

    @Override
    public void delete(League league) 
    {
        entity_manager.getTransaction().begin();
        entity_manager.remove(league);
        entity_manager.getTransaction().commit();
    }
}