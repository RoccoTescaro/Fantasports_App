package jrrt.repositories;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.Team;

@Repository
public interface TeamRepo extends CrudRepository<Team, Long>
{
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    public Set<Team> getByName(String teamName);
}

