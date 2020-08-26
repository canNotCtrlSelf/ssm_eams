package com.cncs.dao;

import com.cncs.domain.Permission;
import com.cncs.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色的dao层
 */
public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cncs.dao.IPermissionDao.findByRoleId")),
    })
    List<Role> findRoleByUserId(String userId);

    //查询所有角色
    @Select("select * from role")
    List<Role> findAll();

    //保存角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    //根据id查找角色
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cncs.dao.IPermissionDao.findByRoleId")),
    })
    Role findById(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRolesByUserId(String userId);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissionsByRoleId(String roleId);

    //为角色添加权限
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
