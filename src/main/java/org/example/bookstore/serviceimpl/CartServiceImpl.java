package org.example.bookstore.serviceimpl;

import org.example.bookstore.dao.CartDao;
import org.example.bookstore.dao.UserDao;
import org.example.bookstore.entity.Cart;
import org.example.bookstore.entity.Book;
import org.example.bookstore.dao.BookDao;
import org.example.bookstore.entity.User;
import org.example.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Cart> findCartByUserId(int userId, Pageable pageable) {
        //根据外键查找购物车中的书籍
        return cartDao.findCartByUserIdAndPage(userId, pageable);
    }

    @Override
    public int findCartNumByUserId(Integer userId) {
        List<Cart> carts = cartDao.findCartByUserId(userId);
        return carts.size();
    }

    @Override
    public boolean addBookToCart(int user_id,int book_id){
        Optional<Cart> matchingCart = cartDao.findCartByUserId(user_id).stream().filter(cart -> cart.getBook().getId() == book_id).findFirst();
        if(matchingCart.isPresent()){
            Cart cart = matchingCart.get();
            cart.setQuantity(cart.getQuantity()+1);
            cartDao.saveCart(cart);
            return true;
        }else{
            Book book = bookDao.findBookById(book_id);
            User user = userDao.findById(user_id);
            Cart cart = new Cart();
//            cart.setUserId(user_id);
//            cart.setBookId(book_id);
            cart.setUser(user);
            cart.setBook(book);
            cart.setQuantity(1);
            cartDao.saveCart(cart);
            return false;
        }
    }

    @Override
    public void deleteBookFromCart(int id){
        cartDao.deleteById(id);
    }

    @Override
    public void updateBookQuantity(int id,int quantity){
        Cart cart = cartDao.findCartById(id);
        cart.setQuantity(quantity);
        cartDao.saveCart(cart);
    }
}