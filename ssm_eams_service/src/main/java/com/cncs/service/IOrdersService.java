package com.cncs.service;

import com.cncs.domain.Orders;

import java.util.List;

/**
 * 订单的业务层接口
 */
public interface IOrdersService {

    public List<Orders> findAll(int page,int size);

    Orders findById(String orderId);
}
