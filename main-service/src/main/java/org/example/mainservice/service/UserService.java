package org.example.mainservice.service;

import org.example.mainservice.dto.UserDto;
import org.example.mainservice.entity.User;
import org.springframework.data.domain.Pageable;

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