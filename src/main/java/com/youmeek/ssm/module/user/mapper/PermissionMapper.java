package com.youmeek.ssm.module.user.mapper;


import com.youmeek.ssm.module.user.pojo.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionMapper {

    public void createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
