package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import oe.entity.User;

public class UserDAOImpl implements UserDAO {
    EntityManager em = OEPU.getEntityManager();

    @Override
    public User create(User entity) {
        try { em.getTransaction().begin(); em.persist(entity); em.getTransaction().commit(); return entity; }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public void update(User entity) {
        try { em.getTransaction().begin(); em.merge(entity); em.getTransaction().commit(); }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public void delete(User entity) {
        try { em.getTransaction().begin(); em.remove(entity); em.getTransaction().commit(); }
        catch (IllegalStateException ex) { em.getTransaction().rollback(); throw ex; }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT o FROM User o", User.class).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return em.createQuery("SELECT o FROM User o WHERE o.email = ?1", User.class)
                .setParameter(1, email).getSingleResult();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            return em.createQuery(
                    "SELECT o FROM User o WHERE o.email = ?1 AND o.password = ?2",
                    User.class)
                    .setParameter(1, email).setParameter(2, password)
                    .getSingleResult();
        } catch (NoResultException e) { return null; }
    }

    @Override
    public boolean emailExists(String email) {
        try { findByEmail(email); return true; }
        catch (NoResultException e) { return false; }
    }
}
