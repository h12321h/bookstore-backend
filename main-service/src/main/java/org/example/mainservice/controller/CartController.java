package org.example.mainservice.controller;

import jakarta.servlet.http.HttpSession;
import org.example.mainservice.dto.UpdateQuantityRequest;
import org.example.mainservice.dto.AddBookRequest;
import org.example.mainservice.entity.Cart;
import org.example.mainservice.service.CartService;
import org.example.mainservice.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/cart")
    public List<Cart> getCart(@RequestParam int page, @RequestParam int size) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id = 0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
            // System.out.println("order  ID: " + user_id);
        }
        Pageable pageable = PageRequest.of(page, size);
        return cartService.findCartByUserId(user_id, pageable);
    }

    @GetMapping("/cart/num")
    public int getCartNum() {
        HttpSession session = SessionUtils.getSession();
        Integer user_id = 0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
            // System.out.println("order  ID: " + user_id);
        }
        return cartService.findCartNumByUserId(user_id);
    }



    @PostMapping("/cart/add")
    public String addBookToCart(@RequestBody AddBookRequest request) {
        Integer user_id = request.getUserId();
        HttpSession session = SessionUtils.getSession();
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
            // System.out.println("order  ID: " + user_id);
        }
        Integer book_id = request.getBookId();
        return cartService.addBookToCart(user_id, book_id);
    }

    @PostMapping("/cart/delete")
    public void deleteBookFromCart(@RequestBody int id) {
        cartService.deleteBookFromCart(id);
    }

    @PostMapping("/cart/update")
    public void updateBookQuantity(@RequestBody UpdateQuantityRequest request) {
        Integer id = request.getId();
        Integer quantity = request.getQuantity();
        cartService.updateBookQuantity(id, quantity);
    }
}
