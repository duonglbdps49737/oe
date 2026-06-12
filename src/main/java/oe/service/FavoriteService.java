package oe.service;

import java.util.List;

import oe.entity.Favorite;

public interface FavoriteService {

	List<Favorite> findAll();

	Favorite findById(Integer id);

	void deleteById(Integer id);

	void create(Favorite entity);

	void update(Favorite entity);

}
