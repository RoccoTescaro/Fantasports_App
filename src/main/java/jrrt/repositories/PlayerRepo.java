package jrrt.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.Player;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Long>
{
    @Query("SELECT p FROM Player p WHERE p.name = ?1")
    public Optional<Player> getByName(String name);

    @Query("SELECT p FROM Player p JOIN p.leagues l WHERE l.id = ?1")
    public Set<Player> getPlayersOfLeague(Long id);
}