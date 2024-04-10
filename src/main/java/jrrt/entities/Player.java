package jrrt.entities;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;


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

    // The league the player belongs to
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    // Users who have purchased the player
    @ManyToMany
    @JoinTable(
            name = "user_players",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
   
    public void setName(String name)
    {
        this.name = name;
    }
}