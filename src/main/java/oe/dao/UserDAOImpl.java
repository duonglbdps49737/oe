package oe.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import oe.entity.User;

public class UserDAOImpl implements UserDAO {

    private EntityManager getEM() {
        return OEPU.getEntityManager();
    }

    @Override
    public User create(User entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
        catch (IllegalStateException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            em.close();
        }
    }

    @Override
    public void update(User entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
        catch (IllegalStateException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            em.close();
        }
    }

    @Override
    public void delete(User entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            // Re-attach entity bằng cách merge trước khi delete
            User managedEntity = em.merge(entity);
            em.remove(managedEntity);
            em.getTransaction().commit();
        }
        catch (IllegalStateException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = getEM();
        try {
            return em.createQuery("SELECT o FROM User o", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User findByEmail(String email) {
        EntityManager em = getEM();
        try {
            return em.createQuery("SELECT o FROM User o WHERE o.email = ?1", User.class)
                    .setParameter(1, email).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = getEM();
        try {
            return em.createQuery(
                            "SELECT o FROM User o WHERE o.email = ?1 AND o.password = ?2",
                            User.class)
                    .setParameter(1, email).setParameter(2, password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean emailExists(String email) {
        try {
            findByEmail(email);
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }
}