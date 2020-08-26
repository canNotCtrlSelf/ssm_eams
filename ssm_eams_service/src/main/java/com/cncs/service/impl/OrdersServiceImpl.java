package com.cncs.service.impl;

import com.cncs.dao.IOrdersDao;
import com.cncs.domain.Orders;
import com.cncs.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    /**
     * 查询所有
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    //查询详情
    @Override
    public Orders findById(String orderId) {
        return ordersDao.findById(orderId);
    }
}
