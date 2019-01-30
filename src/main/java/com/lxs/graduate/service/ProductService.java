package com.lxs.graduate.service;

import com.lxs.graduate.entity.Product;

import java.util.List;

public interface ProductService {


    public Product findProductById(Integer id);

    public List<Product> findProductByUserId(Integer userId);

    int addProduct(Product product);

    int delProduct(Integer id);

    int updateProduct(Product product);


}
