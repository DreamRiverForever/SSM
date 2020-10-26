package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",javaType = Product.class,
                    one = @One(select = "com.itheima.dao.IProductDao.findById"))
    })
    List<Orders> findAll();


    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",javaType = Product.class,
                    one = @One(select = "com.itheima.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,
                    one = @One(select = "com.itheima.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String id);
}
