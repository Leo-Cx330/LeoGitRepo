package com.leo.security.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class UserDto {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;


    private String mobile;


    @Size(max = 100,min = 1,message = "用户年龄必须在0到100之间")
    private String age;
    @Past(message = "用户出生日期必须是过去的日期")
    private Date birthDay;





}
