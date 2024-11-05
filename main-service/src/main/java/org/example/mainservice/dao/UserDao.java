package org.example.mainservice.dao;

import org.example.mainservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);

    void save(User user);
    void delete(int id);

    Page<User> findAll(Pageable pageable);

    Integer getUsersNum();

    Boolean checkUser(String username, String password);
}