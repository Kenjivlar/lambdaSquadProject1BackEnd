package com.loanmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String testRequest() {
        System.out.println("Conexión a API exitosa");
        return "Hello from the TestController";
    }
}
