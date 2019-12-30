package com.example.kafkap.roducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @Autowired
    private KafkaSender<String> kafkaSender;

    @GetMapping("/send")
    public String send(String data) {
        kafkaSender.send(data);
        return "success";
    }
}
