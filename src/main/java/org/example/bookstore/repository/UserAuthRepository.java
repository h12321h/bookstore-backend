package org.example.bookstore.repository;

import org.example.bookstore.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    @Query("SELECT ua FROM UserAuth ua WHERE ua.userId = :userId AND ua.password = :password")
    UserAuth checkUser(Integer userId, String password);
}

