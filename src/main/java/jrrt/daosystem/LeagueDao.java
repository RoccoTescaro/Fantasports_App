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
    private final LeagueRepo leagueRepo;

    @Autowired
    public LeagueDao(LeagueRepo leagueRepo)
    {
        this.leagueRepo = leagueRepo;
    }

    @Override
    public LeagueDao save(League league)
    {
        System.out.println("LeagueDao.save(" + league + ")"); //DEBUG
        leagueRepo.save(league);
        return this;
    }

    @Override
    public LeagueDao delete(League league)
    {
        System.out.println("LeagueDao.delete(" + league + ")"); //DEBUG
        leagueRepo.delete(league);
        return this;
    }

    @Override
    public LeagueDao update(League league, String[] params)
    {
        //leagueRepo.update(league, params);
        return this;
    }

    @Override
    public Set<League> getAll()
    {
        Set<League> leagues = (Set<League>) leagueRepo.findAll();
        return leagues;
    }

    @Override
    public Optional<League> get(Long id)
    {
        return leagueRepo.findById(id);
    }

    public Set<League> getUserAttendedLeagues(Long id)
    {
        return (Set<League>) leagueRepo.getUserAttendedLeagues(id);
    }
}