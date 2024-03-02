package jrrt.entities;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // leghe a cui l'utente è iscritto
    @ManyToMany
    private List<League> attended_leagues; 

    // leghe create dall'utente
    @OneToMany
    private List<League> created_leagues;

    // giocatori acquistati dall'utente
    @OneToMany
    private List<Player> players;

    //JPA must have a default constructor (not necessarily public), 
    //Spring web need it public for the login form

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

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User addAttendedLeague(League league)
    {
        if (this.attended_leagues == null) 
        {
            this.attended_leagues = new ArrayList<League>();
        }
        this.attended_leagues.add(league);
        return this;
    }
}
