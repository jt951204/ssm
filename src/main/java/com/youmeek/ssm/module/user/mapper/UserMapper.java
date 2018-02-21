package com.youmeek.ssm.module.user.mapper;



import com.youmeek.ssm.module.user.pojo.User;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserMapper {

    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRole(Long userId, Long roleId);
    public void uncorrelationRole(Long userId, Long roleId);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
