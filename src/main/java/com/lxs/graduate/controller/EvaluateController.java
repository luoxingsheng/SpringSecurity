package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Evaluate;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.EvaluateService;
import com.lxs.graduate.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    EvaluateService evaluateService;

    DateUtil dateUtil;


    /**
     * 留言
     *
     * @param pId
     * @param content
     * @return
     */
    @PostMapping(value = "/addEvaluate")
    public Map<String,String> leaveWord(@RequestParam("pId")Integer pId,
                              @RequestParam("content")String content) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(date);
        String title=dateStr+"  "+user.getUsername();
        Evaluate evaluate=new Evaluate();
        evaluate.setBuyId(user.getId());
        evaluate.setpId(pId);
        evaluate.setCreateTime(new Timestamp(System.currentTimeMillis()));
        evaluate.setTitle(title);
        evaluate.setMessage(content);
        evaluateService.addEvaluate(evaluate);
        Map<String,String> map=new HashMap<>();
        map.put("messages","添加评论成功！");
         return map;
    }

    /**
     * 获取所有留言
     *
     * @param pId
     * @return
     */
    @PostMapping(value = "/getEvaluates")
    public Map<String,Object> getEvaluates(@RequestParam("pId")Integer pId){
        Map<String,Object> map=new HashMap<>();
        List<Evaluate> list=evaluateService.getAllEvaluatesByPid(pId);
        map.put("words",list);
        return map;
    }
}
