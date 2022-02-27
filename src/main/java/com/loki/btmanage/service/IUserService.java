package com.loki.btmanage.service;

import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.pojo.Role;
import com.loki.btmanage.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @since 2022-02-20
 */
public interface IUserService extends IService<User> {
    /**
     * 用户登录
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 通过用户名获取一个完整的用户对象
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * @param uid
     * @return
     */
    List<Role> getRoles(Integer uid);

    /**
     * 获取所有用户
     * @param keywords
     * @return
     */
    List<User> getAllUsers(String keywords);

    /**
     * 更新用户角色
     * @param uid
     * @param rids
     * @return
     */
    RespBean updateUserRole(Integer uid, Integer[] rids);

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param uid
     * @return
     */
    RespBean updatePassword(String oldPass, String pass, Integer uid);
}
