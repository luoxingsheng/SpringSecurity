package com.lxs.graduate.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxs.graduate.entity.Evaluate;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.service.EvaluateService;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.service.ProductServiceImpl;
import com.lxs.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    EvaluateService evaluateService;

    @RequestMapping("/words")
    public String words(){
        return "/words";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }


    //按照时间顺序查询所有商品，倒叙排列
    @RequestMapping("/")
    public String index(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {

        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.findAllProduct();
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "index";
    }


    @RequestMapping("/index/search")
    public String search(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam("p_name")String p_name) {

        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.searchProducts(p_name);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/searchProduct";
    }


    @GetMapping("/index/livingProducts")
    public String getLivingProducts(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.getLivingProducts();
        model.addAttribute("products",lists);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/livingProduct";
    }

    @GetMapping("/index/clothProducts")
    public String getClothProducts(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.getClothProducts();
        model.addAttribute("products",lists);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/clothProduct";
    }

    @GetMapping("/index/sportProducts")
    public String getSportProducts(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.getSportProducts();
        model.addAttribute("products",lists);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/sportProduct";
    }

    @GetMapping("/index/electricProducts")
    public String getElectricProducts(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.getElectricProducts();
//        model.addAttribute("products",lists);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/electricProduct";
    }

    @GetMapping("/index/bookProducts")
    public String getBookProducts(ModelMap model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Product> lists= productService.getBookProducts();
        model.addAttribute("products",lists);
        PageInfo pageInfo = new PageInfo<Product>(lists, 10);
        model.addAttribute("products",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "products/bookProduct";
    }




    @GetMapping("/index/toProductInfo")
    public String toProductInfo(@RequestParam Integer id,@RequestParam Integer isSeller,ModelMap model){
        Product product=productService.findProductById(id);
        String seller=userService.getUserById(product.getUserId()).getUsername();
        model.addAttribute("pro",product);
        model.addAttribute("isSeller",isSeller);
        model.addAttribute("seller",seller);
        Object pro = JSONObject.toJSON(model);
        return "products/productInfo";
    }

    /**
     * 获取所有留言
     *
     * @param pId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/index/getEvaluates")
    public Map<String,Object> getEvaluates(@RequestParam("pId")Integer pId){
        Map<String,Object> map=new HashMap<>();
        List<Evaluate> list=evaluateService.getAllEvaluatesByPid(pId);
        for(Evaluate str :list){
            System.out.println(str.toString());
        }
        map.put("words",list);
        return map;
    }


}
