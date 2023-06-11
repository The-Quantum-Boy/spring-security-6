package com.sumit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        return "Hi sumit! ";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello sumit bhau! ";
    }
}
