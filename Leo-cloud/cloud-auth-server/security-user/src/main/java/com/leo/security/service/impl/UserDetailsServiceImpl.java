package com.leo.security.service.impl;


import com.alibaba.fastjson.JSON;
import com.leo.security.entity.dto.UserDto;
import com.leo.security.entity.po.User;
import com.leo.security.entity.vo.UserVo;
import com.leo.security.mapper.UserMapper;
import com.leo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService, UserService,SocialUserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userMapper.getUserByUsername(username);
        if (user == null) {


            throw new UsernameNotFoundException("用户不存在");
        }
        return builderUser(user);
    }


    public UserDetails loadUserBySms(String sms) throws UsernameNotFoundException {
      User user = userMapper.getUserByMobile(sms);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return builderUser(user);
    }


    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
       User user = userMapper.getUserByUsername(userId);
        if (user == null) {
            String result = JSON.toJSONString("用户不存在");
            throw new UsernameNotFoundException(result);
        }
        return builderUser(user);
    }

    private SocialUserDetails builderUser(User user){
        return new SocialUser(user.getUsername(),passwordEncoder.encode(user.getPassword()),true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    public UserVo insert(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.insert(userDto);
        UserVo userVo = new UserVo();
        userVo.setId(userDto.getId());
        userVo.setAge(userDto.getAge());
        userVo.setUsername(userDto.getUsername());
        userVo.setBirthDay(userDto.getBirthDay());
        return userVo;
    }


}
