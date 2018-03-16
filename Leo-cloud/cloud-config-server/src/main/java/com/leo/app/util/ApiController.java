package com.leo.app.util;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiController {

    @RequestMapping("/test")
    private String test(@RequestBody String id){

        return id;
    }
}
