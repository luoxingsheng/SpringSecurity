package com.lxs.graduate.service;

import com.lxs.graduate.entity.Product;

import java.util.List;

public interface ProductService {


    public Product findProductById(Integer id);

    public List<Product> findAllProduct( );

    public List<Product> findProductByUserId(Integer userId);

    public List<Product> getLivingProducts();

    public List<Product> getElectricProducts();

    public List<Product> getClothProducts();

    public List<Product> getSportProducts();

    public List<Product> getBookProducts();

    int addProduct(Product product);

    int delProduct(Integer id);

    int updateProduct(Product product);


}
