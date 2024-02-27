package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

import jrrt.entities.League;
import jrrt.entities.Player;
import jrrt.entities.User;

public interface LeagueDao extends Dao<League> 
{
    Optional<List<Player>> getTeam(User user);
}