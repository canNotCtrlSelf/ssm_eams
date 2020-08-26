package com.cncs.service;

import com.cncs.domain.Permission;

import java.util.List;

//权限的业务层接口
public interface IPermissionService {

    //查询所有权限
    List<Permission> findAll();

    //保存新建资源权限
    void save(Permission permission);
}
