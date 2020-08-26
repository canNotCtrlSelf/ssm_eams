package com.cncs.controller;

import com.cncs.domain.Permission;
import com.cncs.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "forward:findAll.do";
    }

    //查询所有权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions= permissionService.findAll();
        mv.addObject("permissionList", permissions);
        mv.setViewName("permission-list");
        return mv;
    }
}
