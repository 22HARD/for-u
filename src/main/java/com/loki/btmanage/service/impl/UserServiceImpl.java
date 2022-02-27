package com.loki.btmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.btmanage.mapper.RoleMapper;
import com.loki.btmanage.mapper.UserRoleMapper;
import com.loki.btmanage.pojo.RespBean;
import com.loki.btmanage.pojo.Role;
import com.loki.btmanage.pojo.User;
import com.loki.btmanage.mapper.UserMapper;
import com.loki.btmanage.pojo.UserRole;
import com.loki.btmanage.service.IUserRoleService;
import com.loki.btmanage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @since 2022-02-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 用户登录
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {

        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用！");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return RespBean.success("登录成功!");
    }

    /**
     * 通过用户名获取一个完整的用户对象
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username)
                .eq("enabled", true));
    }

    /**
     * 根据用户id查询角色列表
     * @param uid
     * @return
     */
    @Override
    public List<Role> getRoles(Integer uid) {
        return roleMapper.getRoles(uid);
    }

    /**
     * 获取所有用户
     * @param keywords
     * @return
     */
    @Override
    public List<User> getAllUsers(String keywords) {
        // 获取当前登录对象
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 传入当前用户 id 是为了不显示当前已经登陆的用户
        return userMapper.getAllUsers(user.getId(), keywords);
    }

    /**
     * 更新用户角色
     * @param uid
     * @param rids
     * @return
     */
    @Override
    public RespBean updateUserRole(Integer uid, Integer[] rids) {

        // 如果设置用户不属于任何角色的话! 那就删除关联表与 uid 的所有数据
        if (rids == null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid", uid);
            if (userRoleService.remove(queryWrapper)){
                return RespBean.success("更新成功!");
            }
            return RespBean.error("更新失败!");
        }

        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("uid", uid));
        Integer result = userRoleMapper.updateUserRole(uid, rids);

        //当影响的行数和修改的行数一样时，就更新成功
        if (rids.length == result) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param uid
     * @return
     */
    @Override
    public RespBean updatePassword(String oldPass, String pass, Integer uid) {
        // 获取用户对象
        User user = userMapper.selectById(uid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,user.getPassword())){
            user.setPassword(encoder.encode(pass));
            int i = userMapper.updateById(user);
            if (i == 1){
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }
}
