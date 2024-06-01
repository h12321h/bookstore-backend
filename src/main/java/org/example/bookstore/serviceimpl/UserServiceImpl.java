package org.example.bookstore.serviceimpl;

import org.example.bookstore.dao.UserDao;
import org.example.bookstore.entity.User;
import org.example.bookstore.entity.UserAuth;
import org.example.bookstore.service.UserService;
import org.example.bookstore.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.bookstore.dto.UserDto;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    @Override
    public UserDto findUserById(int id) {
        User user= userDao.findById(id);
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getAge(), user.getContact(), user.getIntroduction(), user.getAvatar(), user.getType(), user.getBanned());
        return userDto;
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Boolean checkUser(String username, String password) {
        User user = userDao.findByUsername(username);
        return user != null && user.getUserAuth().getPassword().equals(password);
    }

    @Override
    public void saveUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
        userAuth.setPassword(password);
        userAuth.setEmail(email);
        user.setUserAuth(userAuth);
        //如果username开头是Admin_，则设置为管理员
        if (username.startsWith("Admin_")) {
            user.setType(true);
        } else {
            user.setType(false);
        }
        user.setBanned(false);
        user.setAvatar("http://localhost:8080/images/default_avatar.png");
        userDao.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public Boolean existsUser(String username) {
        //输出username
        System.out.println(username);
        return userDao.findByUsername(username) != null;
    }

    @Override
    public Boolean updateUser(UserDto userDto) {
        HttpSession session = SessionUtils.getSession();
        if (session != null ) {
            Integer id = (Integer) session.getAttribute("userId");
            User user = userDao.findById(userDto.getId());
            user.setAge(userDto.getAge());
            user.setContact(userDto.getContact());
            user.setIntroduction(userDto.getIntroduction());
            user.setAvatar(userDto.getAvatar());
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> getUserList(Pageable pageable) {
        Page<User> users = userDao.findAll(pageable);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getAge(), user.getContact(), user.getIntroduction(), user.getAvatar(), user.getType(), user.getBanned());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public Integer getUsersNum() {
        return userDao.getUsersNum();
    }

    @Override
    public Boolean banUser(int id) {
        User user = userDao.findById(id);
        user.setBanned(true);
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean liftUser(int id) {
        User user = userDao.findById(id);
        user.setBanned(false);
        userDao.save(user);
        return true;
    }
}