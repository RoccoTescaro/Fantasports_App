package jrrt.entities;

import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;

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
    private String status;
    private LocalDate start_date;

    // utenti che partecipano alla lega
    @ManyToMany
    private Set<User> participants;

    // giocatori che partecipano alla lega
    @OneToMany
    private Set<Player> players;

    // creatore della lega
    @ManyToOne
    @NonNull
    private User creator;

    public Long getId()
    {
        return this.id;
    }

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


    public String getName()
    {
        return this.name;
    }

    public Set<User> getParticipants()
    {
        if (this.participants == null)
            this.participants = new HashSet<User>();
        return this.participants;
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

    public void  setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return this.status;
    }

    public LocalDate getStartDate() 
    {
        return this.start_date;
    }

    public void setStartDate(LocalDate start_date) 
    {
        this.start_date = start_date;
    }
    public void setCreator(User creator) 
    {
        this.creator = creator;
    }
}