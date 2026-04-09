package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    public DemoController(){
        System.out.println("This is DemoController constructor");
    }

    public void printMessage(String message){
        System.out.println("This is a message from DemoController");
        System.out.println("Message: "+message);
    }
}
