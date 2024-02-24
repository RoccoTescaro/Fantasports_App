package jrrt.core.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>
{
    //@Query("SELECT u FROM User u WHERE u.username = ?1")
    //public Optional<User> getByName(String name);
}