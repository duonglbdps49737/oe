package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.Video;

public class VideoDAOImpl implements VideoDAO{
	EntityManager em = OEPU.getEntityManager();
	
	@Override
	public Video create(Video entity) {
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
	public void update(Video entity) {
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
	public void delete(Video entity) {
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
	public List<Video> findAll() {
		var jpql = "SELECT o FROM Video o";
		var query = em.createQuery(jpql, Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String id) {
		var jpql = "SELECT o FROM Video o WHERE o.id=?1";
		var query = em.createQuery(jpql, Video.class);
		query.setParameter(1, id);
		var results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}
}
