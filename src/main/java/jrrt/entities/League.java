package jrrt.entities;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table
(
    name = "leagues"
)

public class League
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int number_participants;
    private int number_players;
    private int number_formations;

    // utenti che partecipano alla lega
    @ManyToMany
    private List<User> league_participants;

    // giocatori che partecipano alla lega
    @OneToMany
    private List<Player> league_players;

    // creatore della lega
    @ManyToOne
    @NonNull
    private User league_creator;

    public void setName(String name)
    {
        this.name = name;
    }
}