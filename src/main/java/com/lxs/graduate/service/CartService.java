package com.lxs.graduate.service;

import com.lxs.graduate.dao.CartDao;
import com.lxs.graduate.entity.Cart;

import java.util.List;

public interface CartService {

    /**
     * 加入购物车
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    int addCart(Integer userId,Integer productId,int num);

    /**
     * 购物车列表
     * @param userId
     * @return
     */
    List<Cart> getCartList(Integer userId);

    /**
     * 修改数量
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    int updateCartNum(Integer userId,Integer productId,int num);

    /**
     * 全选
     * @param userId
     * @param checked
     * @return
     */
    int checkAll(Integer userId,String checked);

    /**
     * 删除勾选商品
     * @param userId
     * @param productId
     * @return
     */
    int delCartProduct(Integer userId,Integer productId);

    /**
     * 删除购物车
     * @param userId
     * @return
     */
    int delCart(Integer userId);
}
