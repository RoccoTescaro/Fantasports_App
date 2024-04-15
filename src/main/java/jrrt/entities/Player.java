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

    /*@ManyToMany(mappedBy = "players")
    Set<Team> teams = new HashSet<>();*/

    @Override
    public String toString()
    {
        return String.format("Player[id=%d, name='%s', position='%s']", id, name, position);
    }

    public Long getId()
    {
        return this.id;
    }

    //...
}