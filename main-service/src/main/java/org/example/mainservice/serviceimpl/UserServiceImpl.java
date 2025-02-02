package org.example.mainservice.serviceimpl;

import org.example.mainservice.dao.UserDao;
import org.example.mainservice.entity.User;
import org.example.mainservice.entity.UserAuth;
import org.example.mainservice.repository.UserAuthRepository;
import org.example.mainservice.service.UserService;
import org.example.mainservice.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.mainservice.dto.UserDto;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthRepository userAuthRepository;


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
        return userDao.checkUser(username, password);
    }

    @Override
    public void saveUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);

        //如果username开头是Admin_，则设置为管理员
        if (username.startsWith("Admin_")) {
            user.setType(true);
        } else {
            user.setType(false);
        }
        user.setBanned(false);
        user.setAvatar("http://localhost:8080/images/default_avatar.png");
        userDao.save(user);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getId());
        userAuth.setPassword(password);
        userAuth.setEmail(email);
        userAuthRepository.save(userAuth);
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
            user.setUsername(userDto.getUsername());
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