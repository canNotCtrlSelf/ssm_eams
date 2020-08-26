package com.cncs.controller;

import com.cncs.domain.Role;
import com.cncs.domain.UserInfo;
import com.cncs.service.IRoleService;
import com.cncs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    //为用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids", required = true) String[] ids) {
        userService.addRoleToUser(userId,ids);
        return "forward:findAll.do";
    }


    //根据id查找用户和用户没有的角色
    @RequestMapping("/findUserAndAllRoleById.do")
    public ModelAndView findUserAndAllRoleById(@RequestParam(name = "id", required = true) String id) {
        ModelAndView mv = new ModelAndView();
        //根据id查找用户
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        //根据userId查找用户没有的角色
        List<Role> roles = roleService.findOtherRolesByUserId(id);
        mv.addObject("roleList", roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //根据id查寻用户详情
    @RequestMapping("/findById.do")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //保存用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询所有用户
    @RequestMapping("/findAll.do")
    //@Secured("ROLE_ADMIN")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll();
        mv.addObject("userList", userInfoList);
        mv.setViewName("user-list");
        return mv;
    }
}
