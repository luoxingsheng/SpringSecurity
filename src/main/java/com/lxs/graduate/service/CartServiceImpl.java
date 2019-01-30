package com.lxs.graduate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxs.graduate.entity.Cart;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.redis.CartPrefix;
import com.lxs.graduate.redis.RedisServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    RedisServices redisService;

    @Autowired
    ProductService productService;




    @Override
    public int addCart(Integer id1, Integer id2, int num) {
        String userId=id1.toString();
        String productId=id2.toString();
        //key为 userId_cart,校验是否已存在
        boolean exists = redisService.existsValue(CartPrefix.getCartList,userId,productId);
        if (exists){
            //获取现有的购物车中的数据
            String json = redisService.hget(CartPrefix.getCartList,userId,productId);
            if (json !=null){
                //转换为java实体类
                Cart cart = JSON.toJavaObject(JSONObject.parseObject(json),Cart.class);
                cart.setProductNum(cart.getProductNum()+num);
                redisService.hset(CartPrefix.getCartList,userId,productId,JSON.toJSON(cart).toString());
            }else {
                return 0;
            }
            return 1;
        }
        //根据商品id获取商品
        Product product = productService.findProductById(id2);
        if (product==null){
            return 0;
        }
        //设置购物车值
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setProductName(product.getpName());
        cart.setProductIcon(product.getpImg());
        cart.setProductPrice(product.getpPrice());
        cart.setProductStatus(product.getpStatus());
        cart.setProductNum(num);
        cart.setCheck("1");
        redisService.hset(CartPrefix.getCartList,userId,productId,JSON.toJSON(cart).toString());
        return 1;
    }

    /**
     * 展示购物车
     * @param userId
     * @return
     */
    @Override
    public List<Cart> getCartList(Integer id1){
        String userId=id1.toString();
        List<String> jsonList = redisService.hvals(CartPrefix.getCartList,userId);
        List<Cart> cartList = new LinkedList<>();
        for (String json:jsonList){
            Cart cart = JSON.toJavaObject(JSONObject.parseObject(json),Cart.class);
            cartList.add(cart);
        }
        return cartList;
    }

    /**
     * 更新数量
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    @Override
    public int updateCartNum(Integer id1, Integer id2, int num) {
        String userId=id1.toString();
        String productId=id2.toString();
        String json = redisService.hget(CartPrefix.getCartList,userId,productId);
        if (json==null){
            return 0;
        }
        Cart cart = JSON.toJavaObject(JSONObject.parseObject(json),Cart.class);
        cart.setProductNum(num);
        redisService.hset(CartPrefix.getCartList,userId,productId,JSON.toJSON(cart).toString());
        return 1;
    }

    /**
     * 全选商品
     * @param userId
     * @param checked
     * @return
     */
    @Override
    public int checkAll(Integer id1, String checked) {
        String userId=id1.toString();
        //获取商品列表
        List<String> jsonList = redisService.hvals(CartPrefix.getCartList,userId);
        for (String json:jsonList){
            Cart cart = JSON.toJavaObject(JSONObject.parseObject(json),Cart.class);
            if ("true".equals(checked)){
                cart.setCheck("1");
            }else if ("false".equals(checked)){
                cart.setCheck("0");
            }else {
                return 0;
            }
            redisService.hset(CartPrefix.getCartList,userId,cart.getProductId(),JSON.toJSON(cart).toString());
        }
        return 1;
    }

    /**
     * 删除商品
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public int delCartProduct(Integer id1, Integer id2) {
        String userId=id1.toString();
        String productId=id2.toString();
        redisService.hdel(CartPrefix.getCartList,userId,productId);
        return 1;
    }

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    @Override
    public int delCart(Integer id1) {
        String userId=id1.toString();
        redisService.delete(CartPrefix.getCartList,userId);
        return 1;
    }
}
