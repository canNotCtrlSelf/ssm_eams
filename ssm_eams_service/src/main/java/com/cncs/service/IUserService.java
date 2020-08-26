package com.cncs.service;

import com.cncs.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    //添加用户
    void save(UserInfo userInfo);

    //根据id查询用户
    UserInfo findById(String id);

    void addRoleToUser(String userId, String[] ids);
}
