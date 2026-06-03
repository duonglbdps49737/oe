package oe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import oe.entity.Video;

public class VideoDAOImpl implements VideoDAO {
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
		return em.createQuery(
				"SELECT o FROM Video o WHERE o.active = true ORDER BY o.views DESC",
				Video.class).getResultList();
	}

	@Override
	public Video findById(String id) {
		return em.createQuery(
				"SELECT o FROM Video o WHERE o.id = ?1", Video.class)
				.setParameter(1, id).getSingleResult();
	}

	// Phân trang, sắp xếp giảm dần theo views
	@Override
	public List<Video> findByPage(int page, int pageSize) {
		return em.createQuery(
				"SELECT o FROM Video o WHERE o.active = true ORDER BY o.views DESC",
				Video.class)
				.setFirstResult((page - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
	}

	// Đếm tổng video active
	@Override
	public long countAll() {
		return em.createQuery(
				"SELECT COUNT(o) FROM Video o WHERE o.active = true", Long.class)
				.getSingleResult();
	}

	// Tăng views 1 đơn vị
	@Override
	public void increaseViews(String id) {
		try {
			em.getTransaction().begin();
			em.createQuery("UPDATE Video o SET o.views = o.views + 1 WHERE o.id = ?1")
					.setParameter(1, id).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}
}
