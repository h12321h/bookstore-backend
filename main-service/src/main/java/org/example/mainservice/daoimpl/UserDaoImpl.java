package org.example.mainservice.daoimpl;

import org.example.mainservice.dao.UserDao;
import org.example.mainservice.entity.User;
import org.example.mainservice.repository.UserAuthRepository;
import org.example.mainservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
        if(user == null)
            return false;
        return userAuthRepository.checkUser(user.getId(), password);
    }
}
