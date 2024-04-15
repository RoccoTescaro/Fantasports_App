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

    //...
}