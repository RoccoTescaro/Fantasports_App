package jrrt.daosystem;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jrrt.entities.League;
import jrrt.repositories.LeagueRepo;

@Service
public class LeagueDao implements Dao<League> 
{   
    private final LeagueRepo league_repo;
    
    @Autowired
    public LeagueDao(LeagueRepo league_repo)
    {
        this.league_repo = league_repo;
    }

    @Override
    public LeagueDao save(League league)
    {
        league_repo.save(league);
        return this;
    }

    @Override
    public LeagueDao delete(League league)
    {
        league_repo.delete(league);
        return this;
    }

    @Override
    public LeagueDao update(League league, String[] params)
    {
        //league_repo.update(league, params);
        return this;
    }

    @Override
    public Set<League> getAll()
    {
        Set<League> leagues = (Set<League>) league_repo.findAll();
        return leagues;
    }

    @Override
    public Optional<League> get(Long id)
    {
        return league_repo.findById(id);
    }

    /*public Optional<League> getByName(String name)
    {
        return league_repo.getByName(name);
    }

    public List<League> getUserLeagues(String username)
    {
        return league_repo.getUserLeagues(username);
    }*/
}