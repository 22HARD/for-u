package com.loki.btmanage.mapper;

import com.loki.btmanage.pojo.User;
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
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取所有用户
     * @param keywords
     * @return
     */
    List<User> getAllUsers(Integer id, String keywords);
}
