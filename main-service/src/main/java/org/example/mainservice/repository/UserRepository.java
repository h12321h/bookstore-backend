package org.example.mainservice.repository;

import org.example.mainservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("SELECT COUNT(u) FROM User u")
    Integer getUsersNum();
}