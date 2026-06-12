package oe.service;

import java.util.List;

import oe.entity.Video;

public interface VideoService {

	List<Video> findAll();

	Video findById(String id);

	void deleteById(String id);

	void create(Video entity);

	void update(Video entity);

}
