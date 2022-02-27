package com.loki.btmanage.controller;

import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.pojo.User;
import com.loki.btmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private IUserService userService;


    // 获取当前登录用户的信息
    @GetMapping("/info")
    public User getUserInfo(Principal principal){

        if (null==principal){
            return null;
        }
        String username = principal.getName(); // 用户名
        User user = userService.getUserByUserName(username);  // 通过用户名获取一个完整的用户对象
        user.setPassword(null);
        user.setRoles(userService.getRoles(user.getId()));
        return user;
    }

    // 更新用户密码
    @PutMapping("/info/pass")
    public RespBean updateUserPassword(@RequestBody Map<String, String> info) {
        String oldPass = info.get("oldPass");
        String pass = info.get("pass");
        Integer uid = Integer.parseInt(info.get("uid"));
        return userService.updatePassword(oldPass, pass, uid);
    }

    // 修改用户名
    @PutMapping("/info/name")
    public RespBean updateUserName(@RequestBody Map<String, String> info){

        String name = info.get("name");
        Integer uid = Integer.parseInt(info.get("uid"));
        if (name.equals("") || name.equals(null)){
            return RespBean.error("字段不能为空!");
        }
        User user = userService.getById(uid);  // 获取用户对象
        user.setName(name);
        if (userService.updateById(user)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    // 添加用户
    @PostMapping("/")
    public RespBean addUser(@RequestBody Map<String, String> userInfo) {

        String username = userInfo.get("username");  // 账号
        String name = userInfo.get("name");  // 昵称
        User user = userService.getUserByUserName(username);
        if (user != null){
            return RespBean.error("账号重复,创建新用户失败!");
        }
        user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setEnabled(true);
        user.setPassword("$2a$10$Imfuy9ZUJJ7SLqBQhprviOeWeHWWRPaxypvXfSPQe2e/fllfpkUxm");  // 这里是加密后的123456 懒得多一个加密流程了
        user.setUserFace("https://lupic.cdn.bcebos.com/20210629/2001458867_14.jpg");
        if (userService.save(user)){
            return RespBean.success("添加新用户成功!");
        }
        return RespBean.success("添加新用户失败!");
    }

    // 获取所有用户
    @GetMapping("/")
    public List<User> getAllUsers(String keywords) {
        return userService.getAllUsers(keywords);
    }

    // 更新用户
    @PutMapping("/")
    public RespBean updateUser(@RequestBody User user){
        if (userService.updateById(user)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    // 删除用户
    @DeleteMapping("/{uid}")
    public RespBean deleteUser(@PathVariable Integer uid){
        if (userService.removeById(uid)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
