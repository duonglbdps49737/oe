package oe.dao;

import java.util.List;

import oe.entity.Category;

public interface CategoryDAO {
	Category create(Category entity);
	void update(Category entity);
	void delete(Category entity);
	List<Category> findAll();
	Category findById(String id);
}
