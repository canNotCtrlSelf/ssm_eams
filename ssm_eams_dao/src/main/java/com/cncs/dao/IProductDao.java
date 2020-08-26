package com.cncs.dao;

import com.cncs.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品的持久层接口
 */
public interface IProductDao {

    @Select("select * from product where id=#{id}")
    Product findById(String id);

    @Select("select * from product")
    public List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
