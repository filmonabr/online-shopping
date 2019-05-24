package edu.mum.cs425.onlineshopping.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;


@Controller
public class MyErrorController implements ErrorController{

    private final static String ERROR_PATH = "/error";


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
