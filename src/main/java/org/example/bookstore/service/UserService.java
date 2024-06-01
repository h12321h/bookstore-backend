package org.example.bookstore.service;

import org.example.bookstore.dto.UserDto;
import org.example.bookstore.dto.UserRangeDto;
import org.example.bookstore.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserDto findUserById(int id);
    User findUserByUsername(String username);

    Boolean checkUser(String username, String password);

    Boolean existsUser(String username);

    void saveUser(String username, String password, String email);
    void deleteUser(int id);

    Boolean updateUser(UserDto userDto);

    List<UserDto> getUserList(Pageable pageable);

    Integer getUsersNum();

    Boolean banUser(int id);

    Boolean liftUser(int id);

}