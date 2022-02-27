package com.loki.btmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.btmanage.pojo.Menu;
import com.loki.btmanage.pojo.MenuRole;
import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.service.IMenuRoleService;
import com.loki.btmanage.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    // 通过用户id查询菜单列表
    @GetMapping("/")
    public List<Menu> getMenusByUserId(){
        return menuService.getMenusByUserId();
    }

    // 更新角色菜单
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }

    // 根据角色id查询菜单id
    @GetMapping("/{rid}")
    public List<Integer> getByIdMenus(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid))
                //用steam流转出menuRole的菜单id
                .stream().map(MenuRole::getMid/*拿到menuRole的菜单id*/).collect(Collectors.toList());
    }

    /**
     * 通过查询出所有的菜单，，，下一步是查询 用户对应权限所对应的菜单
     * @return
     */
    // 查询所有菜单
    @GetMapping("/all")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }


}
