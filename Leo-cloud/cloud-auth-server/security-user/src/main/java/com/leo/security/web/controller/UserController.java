package com.leo.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.leo.core.properties.SecurityProperties;
import com.leo.security.entity.dto.UserDto;
import com.leo.security.entity.po.User;
import com.leo.security.entity.vo.UserVo;
import com.leo.security.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userDetailServiceImpl;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/register")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }


    @GetMapping("/jwt/me")
    public Object getCurrentUser(HttpServletRequest request) throws Exception {
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();
        return claims;
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping(name = "用户列表查询")
    @JsonView(UserVo.SimpleUserView.class)
    public List<UserVo> getUserList(){
        List<UserVo> userVoList = new ArrayList();
        UserVo userVo =new UserVo();
        userVo.setId(1);
        userVo.setUsername("lihao");
        userVo.setAge("18");
        userVo.setPassword("admin");
        userVo.setBirthDay(new Date());
        userVoList.add(userVo);
        return userVoList;
    }

    @PostMapping(name =  "用户添加",value = "/addUser")
    public UserVo createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult){
         return userDetailServiceImpl.insert(userDto);
    }


    @PostMapping(value = "/binding",name = "绑定")
    public  void binding(UserDto userDto, HttpServletRequest request){
        UserVo userVo = userDetailServiceImpl.insert(userDto);
        providerSignInUtils.doPostSignUp(userVo.getUsername(),new ServletWebRequest(request));
    }


}
