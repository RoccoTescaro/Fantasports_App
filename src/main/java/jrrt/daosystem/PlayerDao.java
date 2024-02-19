package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

public interface PlayerDao extends Dao<Player> 
{
    Optional<Player> findByUsername(String username);
}