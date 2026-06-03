package oe.dao;

import java.util.List;
import oe.entity.Favorite;

public interface FavoriteDAO {
	Favorite create(Favorite entity);
	void update(Favorite entity);
	void delete(Favorite entity);
	List<Favorite> findAll();
	Favorite findById(Integer id);

	// Kiểm tra user đã like video này chưa (trả null nếu chưa)
	Favorite findByUserAndVideo(String email, String videoId);
}
