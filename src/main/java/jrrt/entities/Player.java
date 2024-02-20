package jrrt.entities;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table
(
    name = "players"
)

public class Player
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    

    // lega a cui il giocatore appartiene
    @ManyToOne
    @NonNull
    private League player_league;

    // utenti che hanno acquistato il giocatore
    @ManyToMany
    private List<User> player_users;

    public void setName(String name)
    {
        this.name = name;
    }
}