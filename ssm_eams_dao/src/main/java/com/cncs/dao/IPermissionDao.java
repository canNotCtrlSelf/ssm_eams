package com.cncs.dao;

import com.cncs.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//资源权限表的持久层接口
public interface IPermissionDao {

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    //查询所有权限
    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
