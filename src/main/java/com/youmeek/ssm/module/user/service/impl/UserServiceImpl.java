package com.youmeek.ssm.module.user.service.impl;



import com.youmeek.ssm.module.user.mapper.UserMapper;
import com.youmeek.ssm.module.user.pojo.User;
import com.youmeek.ssm.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordHelper passwordHelper;

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    /**
     * 创建用户
     * @param user
     */
    public void createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userMapper.createUser(user);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user =userMapper.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.updateUser(user);
    }

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds) {
        for (Long roleId:roleIds) {
            userMapper.correlationRole(userId, roleId);
        }
    }


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        for (Long roleId:roleIds) {
            userMapper.uncorrelationRole(userId, roleId);
        }
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        return userMapper.findRoles(username);
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        return userMapper.findPermissions(username);
    }

}
