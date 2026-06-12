package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.Category;

public class CategoryDAOImpl implements CategoryDAO{
	EntityManager em = OEPU.getEntityManager();
	
	@Override
	public Category create(Category entity) {
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
	public void update(Category entity) {
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
	public void delete(Category entity) {
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
	public List<Category> findAll() {
		var jpql = "SELECT o FROM Category o";
		var query = em.createQuery(jpql, Category.class);
		return query.getResultList();
	}

	@Override
	public Category findById(String id) {
		var jpql = "SELECT o FROM Category o WHERE o.id=?1";
		var query = em.createQuery(jpql, Category.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
}