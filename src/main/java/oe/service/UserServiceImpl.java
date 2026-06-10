package oe.service;

import java.util.List;

import oe.dao.UserDAO;
import oe.dao.UserDAOImpl;
import oe.entity.User;

public class UserServiceImpl implements UserService {
	UserDAO dao = new UserDAOImpl();
	
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User findById(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public void deleteByEmail(String email) {
		var user = dao.findByEmail(email);
		dao.delete(user);
	}

	@Override
	public void create(User user) {
		dao.create(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

}
