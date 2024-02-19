package jrrt.daosystem;

import jrrt.entities.User;
import java.util.Optional;

public interface UserDao extends Dao<User> 
{
    Optional<User> findByUsername(String username);
}