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
    private int number_formation;
    private String type;

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

    public void setNumberParticipants(int number_participants)
    {
        this.number_participants = number_participants;
    }

    public void setNumberFormation(int number_formation)
    {
        this.number_formation = number_formation;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setLeagueCreator(User league_creator)
    {
        this.league_creator = league_creator;
    }

    public String getName()
    {
        return this.name;
    }

    public int getNumberParticipants()
    {
        return this.number_participants;
    }

    public int getNumberFormation()
    {
        return this.number_formation;
    }

    public String getType()
    {
        return this.type;
    }

    public String getLeagueCreator()
    {
        return this.league_creator.getUsername();
    }
    @Override
    public String toString()
    {
        return String.format("League[id=%d, name='%s', number_participants='%d', number_formations='%d']", 
                                id, 
                                name, 
                                number_participants, 
                                number_formation);
    }
}