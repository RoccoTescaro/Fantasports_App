
/*package jrrt.repositories;

import jakarta.persistence.EntityManager;

import jrrt.daosystem.LeagueDao;

public class LeagueRepo implements LeagueDao
{
    private EntityManager EntityManager;

    public Optional<List<Player>> getTeam(User user)
    {
        return Optional.ofNullable(em.createQuery("SELECT p FROM Player p WHERE p.team = :team", Player.class)
            .setParameter("team", user.getTeam())
            .getResultList());
    }

    public void setEntityManager(EntityManager em)
    {
        this.em = em;
    }

    public void save(League league)
    {
        em.getTransaction().begin();
        em.persist(league);
        em.getTransaction().commit();
    }

    public void update(League league)
    {
        em.getTransaction().begin();
        em.merge(league);
        em.getTransaction().commit();
    }

    public void delete(League league)
    {
        em.getTransaction().begin();
        em.remove(league);
        em.getTransaction().commit();
    }

    public Optional<League> findById(Long id)
    {
        return Optional.ofNullable(em.find(League.class, id));
    }

    public List<League> findAll()
    {
        return em.createQuery("SELECT l FROM League l", League.class).getResultList();
    }
}*/

package jrrt.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import jrrt.entities.League;
import jrrt.entities.Player;
import jrrt.entities.User;

@Repository
public interface LeagueRepo extends CrudRepository<League, Long>
{
     @Query("SELECT l FROM League l WHERE l.name = ?1")
    public Optional<League> getByName(String name);

    //@Query("SELECT p FROM Player p JOIN p.leagues l WHERE l.name = ?1")
    //public List<Player> findPlayersByLeagueName(String leagueName);


}