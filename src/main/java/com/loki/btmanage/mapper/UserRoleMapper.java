package com.loki.btmanage.mapper;

import com.loki.btmanage.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LOKI
 * @since 2022-02-20
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 更新用户角色
     * @param uid
     * @param rids
     * @return
     */
    Integer updateUserRole(Integer uid, Integer[] rids);
}
