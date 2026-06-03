package oe.service;

import oe.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String email);
}
