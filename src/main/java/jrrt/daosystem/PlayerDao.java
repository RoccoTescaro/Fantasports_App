package jrrt.daosystem;

import jrrt.entities.Player;
import jrrt.repositories.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerDao implements Dao<Player>
{
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerDao(PlayerRepo playerRepo){ this.playerRepo = playerRepo; }

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
        System.out.println("PlayerDao.delete(" + player + ")"); //DEBUG
        playerRepo.delete(player);
        return this;
    }

    @Override
    public PlayerDao update(Player player, String[] params)
    {
        //playerRepo.update(player, params);
        return this;
    }

    @Override
    public Set<Player> getAll()
    {
        return (Set<Player>) playerRepo.findAll();
    }

    @Override
    public Optional<Player> get(Long id)
    {
        return playerRepo.findById(id);
    }
}