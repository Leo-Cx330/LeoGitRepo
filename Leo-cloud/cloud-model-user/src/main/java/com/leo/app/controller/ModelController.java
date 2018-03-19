package com.leo.app.controller;


import com.leo.app.config.ModelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class ModelController {
    @Autowired
    private ModelConfig modelConfig;
    @RequestMapping("/test")
    public Map<String,Object> findFrom(){
        Map<String,Object> retMap = new HashMap<String, Object>();
        retMap.put("username", modelConfig.getUsername());
        return retMap;
    }

}
