package org.example.bookstore.repository;

import org.example.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi WHERE o.user.id=:userId AND oi.book.title = :bookName")
    List<Order> findByUserIdAndBookName(@Param("userId") int userId, @Param("bookName") String bookName);

    @Query("SELECT DISTINCT o FROM Order o WHERE o.user.id=:userId AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByUserIdAndDate(@Param("userId") int userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi WHERE o.user.id=:userId AND oi.book.title = :bookName AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByUserIdAndBookNameAndDate(@Param("userId") int userId, @Param("bookName") String bookName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}