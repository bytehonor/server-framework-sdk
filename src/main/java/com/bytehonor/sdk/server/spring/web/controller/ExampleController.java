package com.bytehonor.sdk.server.spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataString;

@RestController
@RequestMapping("/actuator/example")
public class ExampleController {

    @GetMapping("/hello")
    public DataString hello() {
        return DataString.of("Hello from the ExampleController!");
    }
}
