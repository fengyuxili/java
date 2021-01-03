package com.fxs.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="entry-service")
public interface EntryServiceFeign {

    @RequestMapping(value = "/hi/{name}", method = RequestMethod.GET)
    String sayMsg(@PathVariable("name") String name);

}
