package com.example.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @PostMapping("/add")
    public Subscriber addSubscriber(@RequestBody Subscriber subscriber) {
        if(subscriber.getSubscName() == null || subscriber.getSubscSurname() == null || subscriber.getMsisdn() == null) {
            throw new IllegalArgumentException("Subscriber details cannot be null");
        }
        return subscriberRepository.save(subscriber);
    }
}
