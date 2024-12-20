package jrrt.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int points;

    @ManyToOne
    private User owner;

    @ManyToOne
    private League league;

    @ManyToMany
    @JoinTable(
        name = "pool",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    Set<Player> pool = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "formation",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    Set<Player> formation = new HashSet<>();

    @Override
    public String toString()
    {
        return String.format("Team[id=%d, name='%s', owner='%s', league='%s']", id, name, owner, league);
    }

    public Long getId()
    {
        return this.id;
    }

    public User getOwner()
    {
        return this.owner;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPoints()
    {
        return this.points;
    }

    public Set<Player> getPool()
    {
        return this.pool;
    }

    public League getLeague()
    {
        return this.league;
    }

    public Set<Player> getFormation()
    {
        return this.formation;
    }

    public Team setOwner(User user)
    {
        this.owner = user;
        return this;
    }

    public Team setLeague(League league)
    {
        this.league = league;
        return this;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public Team setName(String name)
    {
        this.name = name;
        return this;
    }


    //...
}