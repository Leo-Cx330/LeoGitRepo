package com.leo.app.service;

import com.leo.app.api.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient("cloud-model-user")
@Component
public interface UserServiceFeign extends UserService {


}
