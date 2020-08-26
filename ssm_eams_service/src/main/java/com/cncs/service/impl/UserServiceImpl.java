package com.cncs.service.impl;

import com.cncs.dao.IUserDao;
import com.cncs.domain.Role;
import com.cncs.domain.UserInfo;
import com.cncs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密
        String password= bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(password);
        userDao.save(userInfo);
    }

    //根据id查询用户
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    //为用户添加角色
    @Override
    public void addRoleToUser(String userId, String[] ids) {
        //通过遍历添加所有角色
        for (String roleId : ids) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    //使用spring-security进行登录验证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的UserInfo对象，封装到UserDetails中，注：User是UserDetails的实现类。
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority());
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //获取权限集合
    private List<GrantedAuthority> getAuthority(List<Role> roles){
        List<GrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    //查询所有用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }
}
