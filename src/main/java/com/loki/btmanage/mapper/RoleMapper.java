package com.loki.btmanage.mapper;

import com.loki.btmanage.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param uid
     * @return
     */
    List<Role> getRoles(Integer uid);
}
