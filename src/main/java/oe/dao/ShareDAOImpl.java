package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.Share;

public class ShareDAOImpl implements ShareDAO{
	EntityManager em = OEPU.getEntityManager();
	
	@Override
	public Share create(Share entity) {
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
	public void update(Share entity) {
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
	public void delete(Share entity) {
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
	public List<Share> findAll() {
		var jpql = "SELECT o FROM Share o";
		var query = em.createQuery(jpql, Share.class);
		return query.getResultList();
	}

	@Override
	public Share findById(Integer id) {
		var jpql = "SELECT o FROM Share o WHERE o.id=?1";
		var query = em.createQuery(jpql, Share.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
}
