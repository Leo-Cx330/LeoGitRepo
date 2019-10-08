package com.leo.app.api;


import com.leo.app.base.common.ResponseBase;
import com.leo.app.entity.CloudUser;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@RequestMapping("/user")
@Component
public interface UserService {

    @RequestMapping("/findAllUser")
     ResponseBase findAllUser();
    @RequestMapping("/registerUser")
     ResponseBase registerUser(@RequestBody CloudUser cloudUser) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    @RequestMapping("/login")
     ResponseBase login(@RequestBody CloudUser cloudUser) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    @RequestMapping("/qqLogin")
     ResponseBase qqLogin(CloudUser cloudUser);
    @RequestMapping("/getUserByOpenId")
     ResponseBase getUserByOpenId(CloudUser cloudUser);


}
