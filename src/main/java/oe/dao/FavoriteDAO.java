package oe.dao;

import java.util.List;
import oe.entity.Favorite;

public interface FavoriteDAO {
    Favorite create(Favorite entity);
    void update(Favorite entity);
    void delete(Favorite entity);
    List<Favorite> findAll();
    Favorite findById(Integer id);
    Favorite findByUserAndVideo(String email, String videoId);
    List<Favorite> findByUser(String email);
}
