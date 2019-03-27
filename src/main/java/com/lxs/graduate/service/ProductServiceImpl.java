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
    public List<Product> findAllProduct() {
        return productMapper.selectAllProduct();
    }

    @Override
    public List<Product> findProductByUserId(Integer userId) {
        return productMapper.findProductByUserId(userId);
    }

    @Override
    public List<Product> getLivingProducts() {
        return productMapper.getLivingProducts();
    }

    @Override
    public List<Product> getElectricProducts() {
        return productMapper.getElectricProducts();
    }

    @Override
    public List<Product> getClothProducts() { return productMapper.getClothProducts(); }

    @Override
    public List<Product> getSportProducts() {
        return productMapper.getSportProducts();
    }

    @Override
    public List<Product> getBookProducts() {
        return productMapper.getBookProducts();
    }

    @Override
    public List<Product> searchProducts(String p_name) {
        return productMapper.searchProducts(p_name);
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
