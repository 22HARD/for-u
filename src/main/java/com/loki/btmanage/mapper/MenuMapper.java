package com.loki.btmanage.mapper;

import com.loki.btmanage.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LOKI
 * @since 2022-02-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户 id 获取对应的id
     * @param uid
     * @return
     */
    List<Menu> getMenusByUserId(Integer uid);

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
