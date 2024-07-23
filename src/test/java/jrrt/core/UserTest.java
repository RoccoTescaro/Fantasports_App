package jrrt.core;

import static org.assertj.core.api.Assertions.assertThat;

import jrrt.daosystem.UserDao;
import jrrt.entities.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() throws Exception
    {
        assertThat(userDao).isNotNull();
    }

    @Test
    void testNumberOfUsers() throws Exception
    {
        int n = 2;
        for (int i = 1; i < n; i++)
            userDao.save(new User());

        assertThat(userDao.getAll().size()).isEqualTo(n);
    }

    @Test
    void testUserCreation()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        User retrievedUser = userDao.getByName("testuser").orElse(null);
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getUsername()).isEqualTo("testuser");
        assertThat(retrievedUser.getPassword()).isEqualTo("testpassword");
    }

    @Test
    void testUserDeletion()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        User retrievedUser = userDao.getByName("testuser").orElse(null);
        assertThat(retrievedUser).isNotNull();

        userDao.delete(retrievedUser);
        User deletedUser = userDao.getByName("testuser").orElse(null);
        assertThat(deletedUser).isNull();
    }

    @Test
    void testModifyUser()
    {
        // Create a new user
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        // Retrieve the user
        User retrievedUser = userDao.getByName("testuser").orElse(null);
        assertThat(retrievedUser).isNotNull();

        // Modify the user
        retrievedUser.setUsername("modifieduser");
        userDao.save(retrievedUser);

        // Retrieve the modified user
        User modifiedUser = userDao.getByName("modifieduser").orElse(null);
        assertThat(modifiedUser).isNotNull();
        assertThat(modifiedUser.getUsername()).isEqualTo("modifieduser");
    }
}