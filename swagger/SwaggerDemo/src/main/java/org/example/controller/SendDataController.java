package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendDataController {

    @PostMapping("/sendData")
    public String sendData(@RequestParam String aNumber, @RequestParam int mb, @RequestParam int rg, @RequestParam String location) {
        return "Data of " + mb + "MB was used by " + aNumber + " with RG " + rg + " in " + location + ".";
    }
}
