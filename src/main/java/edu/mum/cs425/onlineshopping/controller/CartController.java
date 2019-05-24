package edu.mum.cs425.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.mum.cs425.onlineshopping.dto.Item;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.enums.ResultEnum;
import edu.mum.cs425.onlineshopping.exception.MyException;
import edu.mum.cs425.onlineshopping.form.ItemForm;
import edu.mum.cs425.onlineshopping.service.CartService;
import edu.mum.cs425.onlineshopping.service.UserService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String findAll(Model model){
        Collection<Item> items = cartService.findAll();
        BigDecimal total = cartService.getTotal();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "/cart/index";
    }

    @PostMapping("")
    public String addToCart(@Valid ItemForm itemForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            throw new MyException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        cartService.addItem(itemForm);
        return "redirect:" + "/cart";
    }

    @PostMapping("/checkout")
    public  String checkout(Model model, Principal principal) {
        User user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);

        model.addAttribute("msg", ResultEnum.CART_CHECKOUT_SUCCESS.getMessage());
        model.addAttribute("url", "/order");
        return "/common/success";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("product_id") String productId) {
        cartService.removeItem(productId);
        return "redirect:" + "/cart";
    }

    @GetMapping("/change")
    public String plus(@RequestParam("product_id") String poductId, @RequestParam("quantity") Integer quantity) {
        cartService.updateQuantity(poductId, quantity);
        return "redirect:" + "/cart";
    }


}
