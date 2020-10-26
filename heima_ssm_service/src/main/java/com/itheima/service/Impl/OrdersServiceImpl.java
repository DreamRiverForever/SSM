package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) {
        //pageNum页码，pageSize每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        Orders orders = ordersDao.findById(id);
        return orders;
    }
}
