package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.UserDao;
import org.example.bookstore.entity.User;
import org.example.bookstore.entity.UserAuth;
import org.example.bookstore.repository.UserAuthRepository;
import org.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public User findById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUsername(String username) {
        //输出username
        System.out.println(username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Integer getUsersNum() {
        return userRepository.getUsersNum();
    }

    @Override
    public Boolean checkUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return userAuthRepository.checkUser(user.getId(), password)!=null;

    }
}
