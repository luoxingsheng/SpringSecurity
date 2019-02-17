package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.service.ProductServiceImpl;
import com.lxs.graduate.util.DateUtil;
import com.lxs.graduate.util.FileUtil;
import com.lxs.graduate.util.UuidUtil;
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
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Value("${img.productLocation}")
    private  String location;

    UuidUtil util=new UuidUtil();

    DateUtil dateUtil=new DateUtil();

    @Autowired
    ProductService productService=new ProductServiceImpl();

    @PostMapping("/uploadProduct")
    public String uploadProduct(@RequestParam("pImg") MultipartFile file,
                                 @RequestParam String pName,
                                 @RequestParam String pType,
                                 @RequestParam double pPrice,
                                 @RequestParam int pStock,
                                 @RequestParam String pDesc,
                                 ModelMap model) throws FileNotFoundException, IOException, ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fileName = util.getUuid()+".jpg";
        String path="/img/productImg/"+fileName;
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
        product.setpStatus("上架中");//状态待审核
        try {
            FileUtil.uploadFile(file.getBytes(), location, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        productService.addProduct(product);
        Msg msg=new Msg("商品信息","添加商品成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }


    @GetMapping("/toUpdate")
public String toUpdate(@RequestParam Integer id,ModelMap model){
        Product product=productService.findProductById(id);
        model.addAttribute("pro",product);
        return "products/updateProduct";
}


    @PostMapping("/updateProduct")
    public String updateProductById(Product product,ModelMap model)throws FileNotFoundException, IOException{
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
