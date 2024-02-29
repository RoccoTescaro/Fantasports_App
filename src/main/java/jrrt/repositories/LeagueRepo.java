package jrrt.repositories;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.League;

@Repository
public interface LeagueRepo extends CrudRepository<League, Long>
{
    //@Query("SELECT l FROM League l JOIN l.users u WHERE u.username = :username")
    //List<League> getUserLeagues(@Param("username") String username);
}