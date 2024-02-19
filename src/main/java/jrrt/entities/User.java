package jrrt.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Table
(
    name = "users"
)

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    // leghe a cui l'utente è iscritto
    @ManyToMany
    private List<League> attended_leagues; 

    // leghe create dall'utente
    @OneToMany
    private List<League> created_leagues;

    // giocatori acquistati dall'utente
    @OneToMany
    private List<Player> players;

    public void setUsername(String name)
    {
        this.username = name;
    }
}
