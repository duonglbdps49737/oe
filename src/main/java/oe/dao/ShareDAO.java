package oe.dao;

import java.util.List;

import oe.entity.Share;

public interface ShareDAO {
	Share create(Share entity);
	void update(Share entity);
	void delete(Share entity);
	List<Share> findAll();
	Share findById(Integer id);
}
