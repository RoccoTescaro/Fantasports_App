package jrrt.core;

import static org.assertj.core.api.Assertions.assertThat;

import jrrt.daosystem.PlayerDao;
import jrrt.entities.Player;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerTest {

    @Autowired
    private PlayerDao playerDao;



    @Test
    void contextLoads() throws Exception {
        assertThat(playerDao).isNotNull();

    }

    @Test
    void testPlayerCreation()
    {
        Player player = new Player();
        player.setName("Test Player");
        playerDao.save(player);

        Player retrievedPlayer = playerDao.get(player.getId()).orElse(null);
        assertThat(retrievedPlayer).isNotNull();
        assertThat(retrievedPlayer.getName()).isEqualTo("Test Player");
    }

    @Test
    void testPlayerDeletion()
    {
        Player player = new Player();
        player.setName("Test Player");
        playerDao.save(player);

        Player retrievedPlayer = playerDao.get(player.getId()).orElse(null);
        assertThat(retrievedPlayer).isNotNull();

        playerDao.delete(retrievedPlayer);
        Player deletedPlayer = playerDao.get(player.getId()).orElse(null);
        assertThat(deletedPlayer).isNull();
    }

    @Test
    void testModifyPlayer()
    {
        // Create a new player
        Player player = new Player();
        player.setName("Test Player");
        playerDao.save(player);

        // Retrieve the player
        Player retrievedPlayer = playerDao.get(player.getId()).orElse(null);
        assertThat(retrievedPlayer).isNotNull();

        // Modify the player
        retrievedPlayer.setName("Modified Player");
        playerDao.save(retrievedPlayer);

        // Retrieve the modified player
        Player modifiedPlayer = playerDao.get(player.getId()).orElse(null);
        assertThat(modifiedPlayer).isNotNull();
        assertThat(modifiedPlayer.getName()).isEqualTo("Modified Player");
    }



}