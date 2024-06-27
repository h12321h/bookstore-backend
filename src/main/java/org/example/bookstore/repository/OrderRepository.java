package org.example.bookstore.repository;

import org.example.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByUserId(int userId, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi WHERE o.user.id=:userId AND oi.book.title = :bookName")
    Page<Order> findByUserIdAndBookName(@Param("userId") int userId, @Param("bookName") String bookName, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o WHERE o.user.id=:userId AND o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByUserIdAndDate(@Param("userId") int userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi WHERE o.user.id=:userId AND oi.book.title = :bookName AND o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByUserIdAndBookNameAndDate(@Param("userId") int userId, @Param("bookName") String bookName, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT oi.book.title, SUM(oi.quantity), SUM(oi.quantity * oi.price) " +
            "FROM OrderItem oi JOIN oi.order o " +
            "WHERE o.user.id = :userId AND o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.book.title")
    List<Object[]> getBookStatisticByUserId(@Param("userId") int userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.book.title = :bookName")
    Page<Order> findByBookName(Pageable pageable,@Param("bookName") String bookName);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByDate(Pageable pageable, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.book.title = :bookName AND o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByBookNameAndDate(Pageable pageable, @Param("bookName") String bookName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT COUNT(o) FROM Order o JOIN o.orderItems oi WHERE oi.book.title = :bookName")
    Integer findNumByBookName(@Param("bookName") String bookName);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Integer findNumByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT COUNT(o) FROM Order o JOIN o.orderItems oi WHERE oi.book.title = :bookName AND o.orderDate BETWEEN :startDate AND :endDate")
    Integer findNumByBookNameAndDate(@Param("bookName") String bookName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT oi.book.title, SUM(oi.quantity) AS quantity, " +
            "RANK() OVER (ORDER BY SUM(oi.quantity) DESC) AS rank " +
            "FROM OrderItem oi JOIN oi.order o " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.book.id, oi.book.title " +
            "ORDER BY quantity DESC")
    Page<Object[]> getRangeBook(Pageable pageable, @Param("startDate") Date startDate, @Param("endDate") Date endDate);



       @Query("SELECT COUNT(DISTINCT oi.book.id) " +
                "FROM OrderItem oi JOIN oi.order o " +
                "WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Integer getRangeBookNum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT u.username, u.contact, SUM(oi.quantity * oi.price) AS price, " +
            "RANK() OVER (ORDER BY SUM(oi.quantity * oi.price) DESC) AS rank " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "JOIN o.user u " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY u.id,u.username, u.contact " +
            "ORDER BY price DESC")
    Page<Object[]> getRangeUser(Pageable pageable, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT COUNT(DISTINCT u.id) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "JOIN o.user u " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Integer getRangeUserNum(Date startDate, Date endDate);

    @Query("SELECT SUM(oi.quantity) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.user.id = :userId AND o.orderDate BETWEEN :startDate AND :endDate")
    Integer getBookStatisticNum(Integer userId, Date startDate, Date endDate);

    @Query("SELECT SUM(oi.quantity * oi.price) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.user.id = :userId AND o.orderDate BETWEEN :startDate AND :endDate")
    BigDecimal getBookStatisticPrice(Integer userId, Date startDate, Date endDate);

    @Query("SELECT COUNT(o) " +
            "FROM Order o " +
            "WHERE o.user.id = :userId AND o.orderDate BETWEEN :startDate AND :endDate")
    Integer getOrdersNumByUserIdAndDate(Integer userId, Date startDate, Date endDate);

    @Query("SELECT COUNT(o) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.user.id = :userId AND o.orderDate BETWEEN :startDate AND :endDate AND oi.book.title = :bookName")
    Integer getOrdersNumByUserIdAndDateAndBookName(Integer userId, Date startDate, Date endDate, String bookName);
}