package jrrt.repositories;

import java.util.Set;

import jrrt.entities.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.Team;

@Repository
public interface TeamRepo extends CrudRepository<Team, Long>
{
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    public Set<Team> getByName(String teamName);

    @Query("SELECT p FROM Player p JOIN p.teams t WHERE t.id = ?1")
    public Set<Player> getPlayers(Long teamId);
    //get the team by user id
    @Query("SELECT t FROM Team t WHERE t.owner.id = ?1")
    public Team getByUserId(Long userId);

}

