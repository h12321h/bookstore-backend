package org.example.bookstore.service;

import org.example.bookstore.entity.User;

public interface UserService {
    User findUserById(int id);
    User findUserByUsername(String username);

    Boolean checkUser(String username, String password);

    void saveUser(User user);
    void deleteUser(int id);
}