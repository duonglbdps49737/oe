package oe.service;

import java.util.List;

import oe.dao.VideoDAO;
import oe.dao.VideoDAOImpl;
import oe.entity.Video;

public class VideoServiceImpl implements VideoService {
	VideoDAO dao = new VideoDAOImpl();
	
	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

	@Override
	public Video findById(String id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(String id) {
		var Video = dao.findById(id);
		dao.delete(Video);
	}

	@Override
	public void create(Video Video) {
		dao.create(Video);
	}

	@Override
	public void update(Video Video) {
		dao.update(Video);
	}
}
