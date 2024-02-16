package jrrt.solution;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jrrt.solution.League;
import jrrt.solution.Player;

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

    private String nickname;

    // leghe a cui l'utente è iscritto
    @ManyToMany(mappedBy = "users")
    private List<League> attended_leagues; 

    // leghe create dall'utente
    @OneToMany(mappedBy = "creator")
    private List<League> created_leagues;

    // giocatori acquistati dall'utente
    @OneToMany(mappedBy = "owner")
    private List<Player> players;

    public User(String name)
    {
        this.name = name;
    }
}
