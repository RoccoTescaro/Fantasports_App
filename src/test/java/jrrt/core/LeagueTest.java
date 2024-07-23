package jrrt.core;

import static org.assertj.core.api.Assertions.assertThat;

import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.PlayerDao;
import jrrt.daosystem.TeamDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.User;
import jrrt.entities.Player;
import jrrt.entities.Team;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
class LeagueTest {

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlayerDao playerDao;

    @Test
    void contextLoads() throws Exception
    {
        assertThat(leagueDao).isNotNull();
        assertThat(userDao).isNotNull();
        assertThat(playerDao).isNotNull();
    }

    @Test
    void testLeagueCreation()
    {

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user); // Save the user first

        League league = new League();
        league.setName("Test League");
        league.setNParticipants(10);
        league.setNFormation(5);
        league.setType("Test Type");
        league.setStatus("Active");
        league.setStartDate(LocalDate.now());
        league.setCreator(user); // Now set the saved user as the creator
        leagueDao.save(league);

        League retrievedLeague = leagueDao.get(league.getId()).orElse(null);
        assertThat(retrievedLeague).isNotNull();
        assertThat(retrievedLeague.getName()).isEqualTo("Test League");
        assertThat(retrievedLeague.getNParticipants()).isEqualTo(10);
        assertThat(retrievedLeague.getNFormation()).isEqualTo(5);
        assertThat(retrievedLeague.getType()).isEqualTo("Test Type");
        assertThat(retrievedLeague.getStatus()).isEqualTo("Active");
        assertThat(retrievedLeague.getStartDate()).isEqualTo(LocalDate.now());
        assertThat(retrievedLeague.getCreator().getUsername()).isEqualTo("testuser");
        assertThat(retrievedLeague.getCreator().getPassword()).isEqualTo("testpassword");
    }

    @Test
    void testLeagueDeletion()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setNParticipants(10);
        league.setNFormation(5);
        league.setType("Test Type");
        league.setStatus("Active");
        league.setStartDate(LocalDate.now());
        league.setCreator(user);
        leagueDao.save(league);

        League retrievedLeague = leagueDao.get(league.getId()).orElse(null);
        assertThat(retrievedLeague).isNotNull();

        leagueDao.delete(retrievedLeague);
        League deletedLeague = leagueDao.get(league.getId()).orElse(null);
        assertThat(deletedLeague).isNull();
    }

    @Test
    void testGetUserAttendedLeagues()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league1 = new League();
        league1.setName("Test League 1");
        league1.setCreator(user);
        leagueDao.save(league1);

        League league2 = new League();
        league2.setName("Test League 2");
        league2.setCreator(user);
        leagueDao.save(league2);


        Set<League> attendedLeagues = leagueDao.getUserAttendedLeagues(user.getId());

        for (League league : attendedLeagues)
        {
            assertThat(league.getCreator().getUsername()).isEqualTo("testuser");
        }
        //check that in attendedLeagues there are league1 and league2

    }




}