package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.User;

public class UserDAOImpl implements UserDAO{
	EntityManager em = OEPU.getEntityManager();
	
	@Override
	public User create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (IllegalStateException ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (IllegalStateException ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(User entity) {
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (IllegalStateException ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<User> findAll() {
		var jpql = "SELECT o FROM User o";
		var query = em.createQuery(jpql, User.class);
		return query.getResultList();
	}

	@Override
	public User findByEmail(String email) {
		var jpql = "SELECT o FROM User o WHERE o.email=?1";
		var query = em.createQuery(jpql, User.class);
		query.setParameter(1, email);
		return query.getSingleResult();
	}
}
