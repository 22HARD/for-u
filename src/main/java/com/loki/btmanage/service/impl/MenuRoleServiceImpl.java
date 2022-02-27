package com.loki.btmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.btmanage.pojo.MenuRole;
import com.loki.btmanage.mapper.MenuRoleMapper;
import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since 2022-02-20
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色对应的菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //先根据roleId把所有菜单删除
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        //判断是否只更新了一条数据，而那个角色的权限只有一条数据，这代表把这个菜单删除了
        if(null==mids||0==mids.length){
            return RespBean.success("更新成功");
        }
        Integer num = menuRoleMapper.insertRecord(rid,mids);
        if (num==mids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
