package com.loki.btmanage.controller;

import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.pojo.Role;
import com.loki.btmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;


    // 获取所有角色
    @GetMapping("/")
    public List<Role> getAllRole() {
        return roleService.list();
    }

    // 更新用户角色
    @PutMapping("/")
    public RespBean updateUserRole(Integer uid, Integer[] rids){
        return userService.updateUserRole(uid,rids);
    }

    // 添加角色
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        //判断role开头的名字是否是ROLE_开头的，不是的话给他添加ROLE_前缀
        if (!role.getRname().startsWith("ROLE_")) {
            role.setRname("ROLE_" + role.getRname());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    // 删除角色
    @DeleteMapping("/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
