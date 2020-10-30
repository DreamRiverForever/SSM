package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Product;
import com.itheima.service.IPermissionService;
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


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect: findAll.do";
    }
}
