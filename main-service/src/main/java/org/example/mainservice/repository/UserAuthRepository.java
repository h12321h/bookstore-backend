package org.example.mainservice.repository;

import org.example.mainservice.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    @Query("SELECT COUNT(ua) > 0 FROM UserAuth ua WHERE ua.userId = :userId AND ua.password = :password")
    boolean checkUser(Integer userId, String password);
}

