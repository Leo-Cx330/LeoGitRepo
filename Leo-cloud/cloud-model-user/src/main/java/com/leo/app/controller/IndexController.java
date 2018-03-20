package com.leo.app.controller;

import com.leo.app.config.ModelConfig;
import com.leo.app.entity.User;
import com.leo.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/index")
public class IndexController {
    @Autowired
    ModelConfig modelConfig;

    @Autowired
    UserRepository userRepository;
    @RequestMapping("/getUsername")
    public String getUsername(){

      return   modelConfig.getUsername();

    }
    @RequestMapping("/getUser")
    public List<User> getUser(String username){

        return  userRepository.findUserByUsername(username);

    }
}
