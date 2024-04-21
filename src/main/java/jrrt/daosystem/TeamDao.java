package jrrt.daosystem;

import java.util.Optional;
import java.util.Set;

import jrrt.entities.Player;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jrrt.entities.Team;
import jrrt.repositories.TeamRepo;

@Service
public class TeamDao implements Dao<Team> 
{   
    private final TeamRepo teamRepo;
    
    @Autowired
    public TeamDao(TeamRepo teamRepo)
    {
        this.teamRepo = teamRepo;
    }

    @Override
    public TeamDao save(Team team)
    {
        System.out.println("TeamDao.save(" + team + ")"); //DEBUG
        teamRepo.save(team);
        return this;
    }

    @Override
    public TeamDao delete(Team team)
    {
        System.out.println("TeamDao.delete(" + team + ")"); //DEBUG
        teamRepo.delete(team);
        return this;
    }

    @Override
    public TeamDao update(Team team, String[] params)
    {
        //teamRepo.update(team, params);
        return this;
    }

    @Override
    public Set<Team> getAll()
    {
        return (Set<Team>) teamRepo.findAll();
    }

    @Override
    public Optional<Team> get(Long id)
    {
        return teamRepo.findById(id);
    }

    public Set<Player> getPlayers(Long Id)
    {
        return teamRepo.getPlayers(Id);
    }

    public Team getByUserId(Long userId)
    {
        return teamRepo.getByUserId(userId);
    }
}