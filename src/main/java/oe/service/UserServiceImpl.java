package oe.service;

import oe.dao.UserDAO;
import oe.dao.UserDAOImpl;
import oe.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDAOImpl dao = new UserDAOImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User findById(String email) {
        return null;
    }
}
