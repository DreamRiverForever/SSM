package com.itheima.controller;


import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("/findAll.do")
    public ModelAndView finaAll() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = iProductService.findAll();
        modelAndView.addObject("productList",products);
        //System.out.println(products);
        modelAndView.setViewName("product-list");
        return modelAndView;


    }
}
