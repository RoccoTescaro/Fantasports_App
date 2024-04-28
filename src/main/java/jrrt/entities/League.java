package jrrt.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "leagues")
public class League
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int nParticipants;
    private int nFormation;
    private String type;
    private String status;
    private LocalDate startDate;

    @ManyToOne
    private User creator;

    @OneToMany(mappedBy = "league")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany //(fetch = FetchType.EAGER)
    @JoinTable(
        name = "completePool",
        joinColumns = @JoinColumn(name = "league_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> completePool = new HashSet<>();

    @Override
    public String toString()
    {
        return String.format("League[id=%d, name='%s', nParticipants='%d', nFormation='%d', type='%s', status='%s', startDate='%s', creator='%s']", 
                                id, 
                                name, 
                                nParticipants, 
                                nFormation, 
                                type, 
                                status, 
                                startDate, 
                                creator
                            );
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getNParticipants()
    {
        return this.nParticipants;
    }

    public int getNFormation()
    {
        return this.nFormation;
    }

    public String getType()
    {
        return this.type;
    }

    public String getStatus()
    {
        return this.status;
    }

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public Set<Player> getPool()
    {
        return this.completePool;
    }

    public User getCreator()
    {
        return this.creator;
    }

    public League setName(String name)
    {
        this.name = name;
        return this;
    }

    public League setNParticipants(int nParticipants)
    {
        this.nParticipants = nParticipants;
        return this;
    }

    public League setNFormation(int nFormation)
    {
        this.nFormation = nFormation;
        return this;
    }

    public League setType(String type)
    {
        this.type = type;
        return this;
    }

    public League setStatus(String status)
    {
        this.status = status;
        return this;
    }

    public League setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
        return this;
    }

    public League setCreator(User creator)
    {
        this.creator = creator;
        return this;
    }

    public League addPlayer(Player player)
    {
        this.completePool.add(player);
        return this;
    }

    //...
}