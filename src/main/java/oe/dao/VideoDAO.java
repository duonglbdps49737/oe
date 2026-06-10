package oe.dao;

import java.util.List;

import oe.entity.Video;

public interface VideoDAO {
	Video create(Video entity);
	void update(Video entity);
	void delete(Video entity);
	List<Video> findAll();
	Video findById(String id);
}
