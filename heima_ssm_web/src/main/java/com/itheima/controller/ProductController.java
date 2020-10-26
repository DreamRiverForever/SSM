package com.itheima.controller;


import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView finaAll() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = iProductService.findAll();
        modelAndView.addObject("productList",products);
        //System.out.println(products);
        modelAndView.setViewName("product-list1");
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
