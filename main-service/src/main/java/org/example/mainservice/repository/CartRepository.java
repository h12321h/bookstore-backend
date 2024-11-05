package org.example.mainservice.repository;

import org.example.mainservice.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);

    @Query("SELECT DISTINCT c FROM Cart c WHERE c.user.id = :userId")
    Page<Cart> findByUserIdAndPage(@Param("userId") int userId, Pageable pageable);
}

