package com.youmeek.ssm.module.user.mapper;


import com.youmeek.ssm.module.user.pojo.Role;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleMapper {

    public void createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long permissionId);
    public void uncorrelationPermissions(Long roleId, Long permissionId);

}
