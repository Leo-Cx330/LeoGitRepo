package com.leo.security.authentication;


import com.leo.security.entity.dto.UserDto;
import com.leo.security.entity.vo.UserVo;
import com.leo.security.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class DemoConnectionSignUp implements ConnectionSignUp {


    @Autowired
    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {
        UserDto userDto = new UserDto();
        userDto.setUsername(RandomStringUtils.randomNumeric(8));
        userDto.setPassword("123456");
        UserVo userVo =  userService.insert(userDto);
        return userVo.getUsername();
    }
}
