package org.example.bookstore.dao;

import org.example.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);

    void save(User user);
    void delete(int id);

    Page<User> findAll(Pageable pageable);

    Integer getUsersNum();
}