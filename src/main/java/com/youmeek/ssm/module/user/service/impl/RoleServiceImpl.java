package com.youmeek.ssm.module.user.service.impl;


import com.youmeek.ssm.module.user.mapper.RoleMapper;
import com.youmeek.ssm.module.user.pojo.Role;
import com.youmeek.ssm.module.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    public void createRole(Role role) {
         roleMapper.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        for (Long permissionId:permissionIds) {
            roleMapper.correlationPermissions(roleId, permissionId);
        }
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        for (Long permissionId:permissionIds) {
            roleMapper.uncorrelationPermissions(roleId, permissionId);
        }
    }

}
