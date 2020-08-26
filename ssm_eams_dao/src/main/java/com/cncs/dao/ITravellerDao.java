package com.cncs.dao;

import com.cncs.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
}
