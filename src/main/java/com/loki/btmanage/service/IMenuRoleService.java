package com.loki.btmanage.service;

import com.loki.btmanage.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loki.btmanage.pojo.RespBean;

/**
 * @since 2022-02-20
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色对应的菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
