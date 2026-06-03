package oe.service;

import java.util.List;

import oe.entity.User;

public interface UserService {

    List<User> findAll();

    User findById(String email);

    void deleteByEmail(String email);

    void create(User user);

    void update(User user);

}