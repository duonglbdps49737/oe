package oe.service;

import java.util.List;

import oe.dao.FavoriteDAO;
import oe.dao.FavoriteDAOImpl;
import oe.entity.Favorite;

public class FavoriteServiceImpl implements FavoriteService {
	FavoriteDAO dao = new FavoriteDAOImpl();
	
	@Override
	public List<Favorite> findAll() {
		return dao.findAll();
	}

	@Override
	public Favorite findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		var entity = dao.findById(id);
		dao.delete(entity);
	}

	@Override
	public void create(Favorite entity) {
		dao.create(entity);
	}

	@Override
	public void update(Favorite entity) {
		dao.update(entity);
	}
}