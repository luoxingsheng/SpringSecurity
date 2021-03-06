package com.lxs.graduate.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import static com.sun.tools.doclint.Entity.or;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Value("${img.productLocation}")
    private  String location;

    UuidUtil util=new UuidUtil();

    DateUtil dateUtil=new DateUtil();

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    FtpFileUtil ftpFileUtil;

    @Autowired
    BadWordUtil badWordUtil;

    @PostMapping("/uploadProduct")
    public String uploadProduct(@RequestParam("pImg") MultipartFile file,
                                 @RequestParam String pName,
                                 @RequestParam String pType,
                                 @RequestParam double pPrice,
                                 @RequestParam int pStock,
                                 @RequestParam String pDesc,
                                 ModelMap model) throws FileNotFoundException, IOException, ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String[] names = file.getOriginalFilename().split("\\.");
        String fileName = util.getUuid()+"."+names[1];
        InputStream inputStream=file.getInputStream();
        String path="http://47.107.133.187:8080/img/"+fileName;
        java.sql.Date now= new java.sql.Date(new Date().getTime());
        Product product=new Product();
        product.setUserId(user.getId());
        product.setpName(pName);
        product.setpType(pType);
        product.setpPrice(pPrice);
        product.setpStock(pStock);
        product.setpDesc(pDesc);
        product.setpImg(path);
        product.setpTime(now);
        product.setpStatus("上架中");//Status pending review
        try {
             FtpFileUtil.uploadFile(fileName,inputStream);

        } catch (Exception e) {
            // TODO: handle exception
        }

        //Detect no sensitive words directly inserted
        if((!BadWordUtil.isContaintBadWord(product.getpName(),2)) || (!BadWordUtil.isContaintBadWord(product.getpDesc(),2))) {
            productService.addProduct(product);
            Msg msg = new Msg("上传信息", "添加商品成功", null);
            model.addAttribute("message", msg);
            return "notices";
        }
        else{
            Msg msg = new Msg("上传信息", "添加商品失败，商品名称含有敏感词！！！", null);
            model.addAttribute("message", msg);
            return "notices";
        }
    }


    @GetMapping("/toUpdate")
public String toUpdate(@RequestParam Integer id,ModelMap model){
        Product product=productService.findProductById(id);
        model.addAttribute("pro",product);
        return "products/updateProduct";
}


    @PostMapping("/updateProduct")
    public String updateProductById(Product product,ModelMap model)throws FileNotFoundException, IOException{
        java.sql.Date now= new java.sql.Date(new Date().getTime());
        product.setpTime(now);
        productService.updateProduct(product);
        Msg msg=new Msg("商品信息","更新商品信息成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Integer id,ModelMap model){
        productService.delProduct(id);
        Msg msg=new Msg("商品信息","删除商品成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }



}
