package jrrt.daosystem;

import jrrt.entities.Player;

public interface PlayerDao extends Dao<Player> 
{
    int getVote(Player player);

    int getVote(String name);
}