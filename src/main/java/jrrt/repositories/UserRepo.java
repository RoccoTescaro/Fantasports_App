package jrrt.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.League;
import jrrt.entities.User;
import jrrt.entities.League;

@Repository
public interface UserRepo extends CrudRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public Optional<User> getByName(String name);

    @Query("SELECT u.attended_leagues FROM User u WHERE u.username = ?1")
    public Set<League> getUserLeagues(String username);

    //find by id
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public Optional<User> getById(Long id);

}
