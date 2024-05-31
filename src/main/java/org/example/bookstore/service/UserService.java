package org.example.bookstore.service;

import org.example.bookstore.dto.UserDto;
import org.example.bookstore.entity.User;

public interface UserService {
    UserDto findUserById(int id);
    User findUserByUsername(String username);

    Boolean checkUser(String username, String password);

    Boolean existsUser(String username);

    void saveUser(String username, String password, String email);
    void deleteUser(int id);

    Boolean updateUser(UserDto userDto);
}