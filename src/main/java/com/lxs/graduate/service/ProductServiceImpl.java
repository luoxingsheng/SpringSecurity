package com.lxs.graduate.service;

import com.lxs.graduate.dao.ProductMapper;
import com.lxs.graduate.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product findProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findProductByUserId(Integer userId) {
        return productMapper.findProductByUserId(userId);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int delProduct(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }
}
