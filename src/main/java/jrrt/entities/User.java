package jrrt.entities;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "creator")
    private Set<League> createdLeagues = new HashSet<>();


    @Override
    public String toString()
    {
        return String.format("User[id=%d, username='%s', password='%s']", id, username, password);
    }

    public Long getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    //setters are mostly needed for ModelAttribute in Spring
    public User setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    //...
}
