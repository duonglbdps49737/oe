package oe.dao;

import java.util.List;

import oe.entity.User;

public interface UserDAO {
	User create(User entity);
	void update(User entity);
	void delete(User entity);
	List<User> findAll();
	User findByEmail(String email);
}
