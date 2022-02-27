package com.loki.btmanage.controller;

import com.loki.btmanage.pojo.UserLoginParam;
import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登录
 **/
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    // 登录
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),request);
    }


    // 退出登录
    @PostMapping("/logout")
    public RespBean logout(){
        // 前端自行销毁token即可
        return RespBean.success("注销成功！");
    }
}
