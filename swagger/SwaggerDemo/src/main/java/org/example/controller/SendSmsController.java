package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendSmsController {

    @PostMapping("/sendSms")
    public String sendSms(@RequestParam String aNumber, @RequestParam String bNumber, @RequestParam String location) {
        return "A SMS was sent from " + aNumber + " to " + bNumber + " in " + location + ".";
    }
}
