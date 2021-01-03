package com.fxs.feign.controller;

import com.fxs.feign.service.EntryServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private EntryServiceFeign entryServiceFeign;
    @GetMapping("/hi/{name}")
    public String hi(@PathVariable String name) {
       return entryServiceFeign.sayMsg(name);
    }
}
