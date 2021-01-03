package com.fxs.router.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable String name) {
        return "Hi 2 "+ name;
    }
}
