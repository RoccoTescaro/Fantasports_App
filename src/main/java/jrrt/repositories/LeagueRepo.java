package jrrt.repositories;

import java.util.Set;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.League;

@Repository
public interface LeagueRepo extends CrudRepository<League, Long>
{
    @Query("SELECT l FROM League l WHERE l.creator.id = ?1")
    public Set<League> getUserCreatedLeagues(Long id);

    @Query("SELECT l FROM League l JOIN l.teams t WHERE t.owner.id = ?1")
    public Set<League> getUserAttendedLeagues(Long id);

    @Query("SELECT l FROM League l WHERE l.name = ?1")
    public Set<League> getByName(String name);
}

