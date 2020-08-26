package com.cncs.service.impl;

import com.cncs.dao.IPermissionDao;
import com.cncs.domain.Permission;
import com.cncs.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    //查询所有权限
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    //保存权限
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
