package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

public interface LeagueDao extends Dao<League> 
{
    Optional<List<Player>> getTeam(User user);
}