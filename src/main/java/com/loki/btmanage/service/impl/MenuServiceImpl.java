package com.loki.btmanage.service.impl;

import com.loki.btmanage.pojo.Menu;
import com.loki.btmanage.mapper.MenuMapper;
import com.loki.btmanage.pojo.User;
import com.loki.btmanage.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @since 2022-02-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过用户 id 获取对应的菜单信息列表
     * @return
     */
    @Override
    public List<Menu> getMenusByUserId() {
        Integer uid = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return menuMapper.getMenusByUserId(uid);
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 通过查询出所有的菜单，，，下一步是查询 用户对应权限所对应的菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
