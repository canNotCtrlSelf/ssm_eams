package com.cncs.service;

import com.cncs.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    void save(Product product);
}
