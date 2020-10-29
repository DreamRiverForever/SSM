package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单-未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll(){
//        ModelAndView modelAndView = new ModelAndView();
//        List<Orders> orders = ordersService.findAll();
//        System.out.println(orders);
//        modelAndView.addObject("ordersList",orders);
//        modelAndView.setViewName("orders-list");
//        return modelAndView;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        

        return modelAndView;
    }

}
