package com.loki.btmanage.service;

import com.loki.btmanage.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @since 2022-02-20
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户 id 获取对应的菜单信息列表
     * @return
     */
    List<Menu> getMenusByUserId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 通过查询出所有的菜单，，，下一步是查询 用户对应权限所对应的菜单
     * @return
     */
    List<Menu> getAllMenus();
}
