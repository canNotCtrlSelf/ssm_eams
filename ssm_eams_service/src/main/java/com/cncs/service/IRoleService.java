package com.cncs.service;

import com.cncs.domain.Permission;
import com.cncs.domain.Role;

import java.util.List;

public interface IRoleService {

    Role findById(String id);

    List<Role> findAll();

    void save(Role role);

    //查找用户没有的角色
    List<Role> findOtherRolesByUserId(String id);

    List<Permission> findOtherPermissionsByRoleId(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
