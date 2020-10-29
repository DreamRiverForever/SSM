package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateStringEditor());
    }

    @Autowired
    private IProductService iProductService;
    //查询所有产品
    @RequestMapping("/findAll.do")
    public ModelAndView finaAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page, @RequestParam(name = "size",required = true,defaultValue = "4")int size) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = iProductService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(products);
        modelAndView.addObject("pageInfo",pageInfo);
        //System.out.println(products);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        System.out.println(product);
        iProductService.save(product);
        return "redirect:findAll.do";


    }
}
