package com.lxs.graduate.dao;

import com.lxs.graduate.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAllProduct();

    List<Product> getLivingProducts();

    List<Product> getElectricProducts();

    List<Product> getClothProducts();

    List<Product> getSportProducts();

    List<Product> getBookProducts();

    List<Product> searchProducts(@Param("p_name")String p_name);

    List<Product> findProductByUserId(Integer userId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}