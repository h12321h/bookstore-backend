package org.example.bookstore.dao;

import org.example.bookstore.entity.User;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);

    void save(User user);
    void delete(int id);

}