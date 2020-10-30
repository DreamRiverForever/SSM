package com.itheima.controller;


import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        modelAndView.addObject("userList",users);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo info){
        userService.save(info);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo info = userService.findById(id);
        modelAndView.addObject("user",info);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1 根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2 根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId); //角色是可以多选的
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }


}
