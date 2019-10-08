package com.leo.security.mapper;

import com.leo.security.entity.dto.UserDto;
import com.leo.security.entity.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;


public interface UserMapper {

     @Select("select * from user where username =#{username} ")
     User getUserByUsername(@Param("username") String username);

     @Select("select * from user where mobile = #{mobile}")
     User getUserByMobile(@Param("mobile") String mobile);

     @Select("select * from user  where id =#{userId}")
     User getUserId(@Param("userId") String userId);

     @Insert("insert into user (id,username,password,age) values (#{id},#{username},#{password},#{age})")
     @SelectKey(keyProperty = "id", resultType = String.class, before = true,
             statement = "select replace(uuid(), '-', '') as id from dual")
             int insert(UserDto userDto);
}
