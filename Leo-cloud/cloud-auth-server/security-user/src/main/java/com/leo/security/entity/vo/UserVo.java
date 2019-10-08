package com.leo.security.entity.vo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;


@Data
public class UserVo {

    public interface SimpleUserView{};
    public interface PasswordUserView extends  SimpleUserView{}



    @JsonView(SimpleUserView.class)
    private Integer id;

    @JsonView(SimpleUserView.class)
    private String username;

    @JsonView(PasswordUserView.class)
    private String password;

    @JsonView(SimpleUserView.class)
    private String age;

    @JsonView(SimpleUserView.class)
    private Date birthDay;

    @JsonView(SimpleUserView.class)
    private String mobile;


}
