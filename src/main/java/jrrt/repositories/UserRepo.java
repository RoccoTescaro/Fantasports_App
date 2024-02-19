package jrrt.repositories;

import jakarta.persistence.EntityManager;

import jrrt.daosystem.UserDao;

@Repository
public interface UserRepo implements UserDao 
{
    @Query ("SELECT u FROM User u WHERE u.username = :username")
    @Override
    public Optional<User> findByUsername(String username);

    @Query ("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password);

    @Query ("SELECT u FROM User u WHERE u.email = :email")
    @Override
    public Optional<User> findByEmail(String email);

    @Query ("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password);

    @Query ("SELECT u FROM User u WHERE u.id = :id")
    @Override
    public Optional<User> findById(Long id);

    @Query ("SELECT u FROM User u")
    @Override
    public List<User> findAll();

    @Override
    public void save(User user);

    @Override
    public void update(User user);

    @Override
    public void delete(User user);
}