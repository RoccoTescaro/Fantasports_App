package jrrt.repositories;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.League;
import jrrt.entities.Player;
import jrrt.entities.Team;

@Repository
public interface LeagueRepo extends CrudRepository<League, Long>
{
    @Query("SELECT l FROM League l WHERE l.name = ?1")
    public Set<League> getByName(String name);

    @Query("SELECT p FROM Player p JOIN p.leagues l WHERE l.id = ?1")
    public Set<Player> getPlayers(Long id);
    
    @Query("SELECT t FROM Team t WHERE t.league.id = ?1")
    public Set<Team> getTeams(Long id);

    @Query("SELECT l FROM League l JOIN l.teams t WHERE t.owner.id = ?1")
    public Set<League> getUserAttendedLeagues(Long id);
}

