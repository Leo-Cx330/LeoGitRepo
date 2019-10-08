package com.leo.security.service;


import com.leo.security.entity.dto.UserDto;
import com.leo.security.entity.vo.UserVo;

public interface UserService {

    UserVo insert(UserDto userDto);
}
