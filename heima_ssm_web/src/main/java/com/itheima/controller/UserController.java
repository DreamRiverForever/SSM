package com.itheima.controller;


import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
