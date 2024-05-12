package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.UserDao;
import org.example.bookstore.entity.User;
import org.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        return userRepository.getOne(id);
    }
}
