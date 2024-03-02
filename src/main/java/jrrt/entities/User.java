package jrrt.entities;

import java.util.Set;
import java.util.HashSet;

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
    private Set<League> attended_leagues; 

    // leghe create dall'utente
    @OneToMany
    private Set<League> created_leagues;

    // giocatori acquistati dall'utente
    @OneToMany
    private Set<Player> players;

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

    public Set<League> getAttendedLeagues()
    {
        if (this.attended_leagues == null)
            this.attended_leagues = new HashSet<League>();
        return this.attended_leagues;
    }
}
