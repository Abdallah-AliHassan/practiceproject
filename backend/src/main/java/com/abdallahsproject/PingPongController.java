package com.abdallahsproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    private static int COUNTER = 0;

    record PingPong(String res){}

    @GetMapping("/ping")
    public PingPong getPingPong() {
        return new PingPong("pong" + ++COUNTER);
    }
}
