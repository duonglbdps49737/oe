package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import oe.entity.Favorite;

public class FavoriteDAOImpl implements FavoriteDAO {
    EntityManager em = OEPU.getEntityManager();

    @Override
    public Favorite create(Favorite entity) {
        try { em.getTransaction().begin(); em.persist(entity); em.getTransaction().commit(); return entity; }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public void update(Favorite entity) {
        try { em.getTransaction().begin(); em.merge(entity); em.getTransaction().commit(); }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public void delete(Favorite entity) {
        try { em.getTransaction().begin(); em.remove(em.contains(entity) ? entity : em.merge(entity)); em.getTransaction().commit(); }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public List<Favorite> findAll() {
        return em.createQuery("SELECT o FROM Favorite o", Favorite.class).getResultList();
    }

    @Override
    public Favorite findById(Integer id) {
        return em.find(Favorite.class, id);
    }

    @Override
    public Favorite findByUserAndVideo(String email, String videoId) {
        try {
            return em.createQuery(
                    "SELECT o FROM Favorite o WHERE o.user.email = ?1 AND o.video.id = ?2",
                    Favorite.class)
                    .setParameter(1, email).setParameter(2, videoId).getSingleResult();
        } catch (NoResultException e) { return null; }
    }

    @Override
    public List<Favorite> findByUser(String email) {
        return em.createQuery(
                "SELECT o FROM Favorite o WHERE o.user.email = ?1 ORDER BY o.likeDate DESC",
                Favorite.class)
                .setParameter(1, email).getResultList();
    }
}
