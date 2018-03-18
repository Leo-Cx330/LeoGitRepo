package com.leo.app.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ModelController {

    @Value("${username}")
    private String username;


    @RequestMapping("/findFrom")
    public Map<String,Object> findFrom(){
        Map<String,Object> retMap = new HashMap<String, Object>();
        retMap.put("url", username);
        return retMap;
    }

}
