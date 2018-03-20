package com.leo.app.repository;

import com.leo.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{


        //查询用户名称包含username字符串的用户对象
    List<User> findUserByUsername(String username);

}
