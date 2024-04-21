package jrrt.daosystem;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jrrt.repositories.PlayerRepo;
import jrrt.entities.Player;

@Service
public class PlayerDao implements Dao<Player> 
{
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerDao(PlayerRepo playerRepo)
    {
        this.playerRepo = playerRepo;
    }

    @Override
    public PlayerDao save(Player player)
    {
        System.out.println("PlayerDao.save(" + player + ")"); //DEBUG
        playerRepo.save(player);
        return this;
    }

    @Override
    public PlayerDao delete(Player player)
    {
        System.out.println("LeagueDao.delete(" + player + ")"); //DEBUG
        playerRepo.delete(player);
        return this;
    }

    @Override
    public PlayerDao update(Player player, String[] params)
    {
        //leagueRepo.update(league, params);
        return this;
    }

    @Override
    public Set<Player> getAll()
    {
        Set<Player> players = (Set<Player>) playerRepo.findAll();
        return players;
    }

    @Override
    public Optional<Player> get(Long id)
    {
        return playerRepo.findById(id);
    }

    public Optional<Player> getByName(String name)
    {
        return playerRepo.getByName(name);
    }

    public Set<Player> getPlayersOfLeague(Long id)
    {
        return playerRepo.getPlayersOfLeague(id);
    }

    //...
}