package edu.mum.cs425.onlineshopping.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.mum.cs425.onlineshopping.exception.MyException;


@ControllerAdvice

public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    public String errorHandler(MyException ex, Model model){
        model.addAttribute("msg", ex.getMessage());
        return "/common/error";
    }

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception ex, Model model){
        model.addAttribute("msg", "Unknown Error!");
        return "/common/error";
    }


}
