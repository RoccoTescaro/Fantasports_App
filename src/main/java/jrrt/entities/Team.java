package jrrt.entities;

import java.util.Set;

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

    /*@ManyToMany(mappedBy = "teams")
    Set<Player> players;*/

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


    //...
}