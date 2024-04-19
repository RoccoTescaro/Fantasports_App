package jrrt.entities;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private int vote;

    @ManyToMany(mappedBy = "players")
    Set<Team> teams = new HashSet<>();

    @Override
    public String toString()
    {
        return String.format("Player[id=%d, name='%s', position='%s']", id, name, position);
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Set<Team> getTeams()
    {
        return this.teams;
    }

    public int getVote()
    {
        return this.vote;
    }

    public Player setName(String name)
    {
        this.name = name;
        return this;
    }

    public Player setTeam(Team team)
    {
        this.teams.add(team);
        team.getPlayers().add(this);
        return this;
    }

    public Player setVote(int vote)
    {
        this.vote = vote;
        return this;
    }
    //...
}