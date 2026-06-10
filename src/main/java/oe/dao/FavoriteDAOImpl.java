package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.Favorite;

public class FavoriteDAOImpl implements FavoriteDAO{
	EntityManager em = OEPU.getEntityManager();
	
	@Override
	public Favorite create(Favorite entity) {
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
	public void update(Favorite entity) {
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
	public void delete(Favorite entity) {
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
	public List<Favorite> findAll() {
		var jpql = "SELECT o FROM Favorite o";
		var query = em.createQuery(jpql, Favorite.class);
		return query.getResultList();
	}

	@Override
	public Favorite findById(Integer id) {
		var jpql = "SELECT o FROM Favorite o WHERE o.id=?1";
		var query = em.createQuery(jpql, Favorite.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
}