package com.youmeek.ssm.module.user.service.impl;


import com.youmeek.ssm.module.user.mapper.PermissionMapper;
import com.youmeek.ssm.module.user.pojo.Permission;
import com.youmeek.ssm.module.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    public void createPermission(Permission permission) {
         permissionMapper.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionMapper.deletePermission(permissionId);
    }
}
