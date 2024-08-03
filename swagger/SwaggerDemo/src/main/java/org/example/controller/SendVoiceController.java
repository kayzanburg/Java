package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendVoiceController {

    @PostMapping("/sendVoice")
    public String sendVoice(@RequestParam String aNumber, @RequestParam String bNumber, @RequestParam int duration, @RequestParam String location) {
        return "A call was made from " + aNumber + " to " + bNumber + " for " + duration + " minutes in " + location + ".";
    }
}
