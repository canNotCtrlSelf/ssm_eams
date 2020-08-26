package com.cncs.dao;

import com.cncs.domain.Member;
import com.cncs.domain.Orders;
import com.cncs.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单的持久层接口
 */
public interface IOrdersDao {

    /**
     * 查询所有订单
     *
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.cncs.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();


    @Select("select * from orders where id = #{orderId}")
    @Results({
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.cncs.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.cncs.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cncs.dao.ITravellerDao.findByOrderId"))
    })
    Orders findById(String orderId);
}
