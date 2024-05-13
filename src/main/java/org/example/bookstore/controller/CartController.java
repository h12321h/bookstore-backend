package org.example.bookstore.controller;

import org.example.bookstore.dto.UpdateQuantityRequest;
import org.example.bookstore.dto.AddBookRequest;
import org.example.bookstore.entity.Cart;
import org.example.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public List<Cart> getCart(@RequestBody int user_id) {
        return cartService.findCartByUserId(user_id);
    }

    @PostMapping("/cart/add")
    public String addBookToCart(@RequestBody AddBookRequest request) {
        Integer user_id = request.getUserId();
        Integer book_id = request.getBookId();
        boolean state=cartService.addBookToCart(user_id, book_id);
        System.out.println(state);
        if(state){
           return "add";
        }else{
            return "success";
        }
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
