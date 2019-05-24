package edu.mum.cs425.onlineshopping.service;

import edu.mum.cs425.onlineshopping.dto.Item;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.form.ItemForm;

import java.math.BigDecimal;
import java.util.Collection;


public interface CartService {
    void addItem(ItemForm itemForm);
    void removeItem(String productId);
    void updateQuantity(String productId, Integer quantity);

    Collection<Item> findAll();

    void  checkout(User user);

    BigDecimal getTotal();

}
