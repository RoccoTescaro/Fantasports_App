package jrrt.core;

import static org.assertj.core.api.Assertions.assertThat;

import jrrt.daosystem.PlayerDao;
import jrrt.daosystem.LeagueDao;
import jrrt.daosystem.TeamDao;
import jrrt.daosystem.UserDao;
import jrrt.entities.League;
import jrrt.entities.Team;
import jrrt.entities.User;
import jrrt.entities.Player;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class TeamTest {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private PlayerDao playerDao;

    @Test
    void contextLoads() throws Exception
    {
        assertThat(teamDao).isNotNull();
        assertThat(userDao).isNotNull();
        assertThat(leagueDao).isNotNull();
        assertThat(playerDao).isNotNull();
    }



    @Test
    void testTeamCreation()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setCreator(user);
        leagueDao.save(league);

        Team team = new Team();
        team.setName("Test Team");
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);

        Team retrievedTeam = teamDao.get(team.getId()).orElse(null);
        assertThat(retrievedTeam).isNotNull();
        assertThat(retrievedTeam.getName()).isEqualTo("Test Team");
        assertThat(retrievedTeam.getOwner().getUsername()).isEqualTo("testuser");
        assertThat(retrievedTeam.getLeague().getName()).isEqualTo("Test League");
    }

    @Test
    void testTeamDeletion()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setCreator(user);
        leagueDao.save(league);

        Team team = new Team();
        team.setName("Test Team");
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);

        Team retrievedTeam = teamDao.get(team.getId()).orElse(null);
        assertThat(retrievedTeam).isNotNull();

        teamDao.delete(retrievedTeam);
        Team deletedTeam = teamDao.get(team.getId()).orElse(null);
        assertThat(deletedTeam).isNull();
    }

    @Test
    void testModifyTeam()
    {
        // Create a new team
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setCreator(user);
        leagueDao.save(league);

        Team team = new Team();
        team.setName("Test Team");
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);

        // Retrieve the team
        Team retrievedTeam = teamDao.get(team.getId()).orElse(null);
        assertThat(retrievedTeam).isNotNull();

        // Modify the team
        retrievedTeam.setName("Modified Team");
        teamDao.save(retrievedTeam);

        // Retrieve the modified team
        Team modifiedTeam = teamDao.get(team.getId()).orElse(null);
        assertThat(modifiedTeam).isNotNull();
        assertThat(modifiedTeam.getName()).isEqualTo("Modified Team");
    }

    @Test
    void testGetTeamByOwnerAndLeague()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setCreator(user);
        leagueDao.save(league);

        Team team = new Team();
        team.setName("Test Team");
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);

        Team retrievedTeam = teamDao.getTeamByOwnerAndLeague(user, league);
        assertThat(retrievedTeam).isNotNull();
        assertThat(retrievedTeam.getName()).isEqualTo("Test Team");
        assertThat(retrievedTeam.getOwner().getUsername()).isEqualTo("testuser");
        assertThat(retrievedTeam.getLeague().getName()).isEqualTo("Test League");
    }

    @Test
    void testGetPoolByTeamId()
    {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userDao.save(user);

        League league = new League();
        league.setName("Test League");
        league.setCreator(user);
        leagueDao.save(league);

        Team team = new Team();
        team.setName("Test Team");
        team.setOwner(user);
        team.setLeague(league);
        teamDao.save(team);

        Player player1 = new Player();
        player1.setName("Player 1");
        playerDao.save(player1);

        Player player2 = new Player();
        player2.setName("Player 2");
        playerDao.save(player2);

        team.getPool().add(player1);
        team.getPool().add(player2);
        teamDao.save(team);

        Set<Player> pool = teamDao.getPoolByTeamId(team.getId());
        Player retrievedPlayer1 = pool.stream().filter(p -> p.getName().equals("Player 1")).findFirst().orElse(null);
        Player retrievedPlayer2 = pool.stream().filter(p -> p.getName().equals("Player 2")).findFirst().orElse(null);
        assertThat(retrievedPlayer1.getName()).isEqualTo(player1.getName());
        assertThat(retrievedPlayer2.getId()).isEqualTo(player2.getId());
        assertThat(retrievedPlayer2.getName()).isEqualTo(player2.getName());
        assertThat(retrievedPlayer2.getId()).isEqualTo(player2.getId());
        assertThat(pool.size()).isEqualTo(2);

    }

}

