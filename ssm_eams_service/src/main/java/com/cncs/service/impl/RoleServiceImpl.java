package com.cncs.service.impl;

import com.cncs.dao.IRoleDao;
import com.cncs.domain.Permission;
import com.cncs.domain.Role;
import com.cncs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findOtherRolesByUserId(String userId) {
        return roleDao.findOtherRolesByUserId(userId);
    }

    @Override
    public List<Permission> findOtherPermissionsByRoleId(String roleId) {
        return roleDao.findOtherPermissionsByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        //遍历权限数组
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
