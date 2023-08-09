package com.abdallahsproject.Customer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    private static int COUNTER = 0;

    record PingPong(String res){}

    @GetMapping("/echo")
    public PingPong getPingPong() {
        return new PingPong("Hello0o0   " + ++COUNTER);
    }
}
