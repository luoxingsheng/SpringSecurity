package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Evaluate;
import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.EvaluateService;
import com.lxs.graduate.util.BadWordUtil;
import com.lxs.graduate.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
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

    @Autowired
    BadWordUtil badWordUtil;

    /**
     * 留言
     *
     * @param pId
     * @param content
     * @return
     */
    @PostMapping(value = "/addEvaluate")
    public Map<String,Object> leaveWord(@RequestParam("pId")Integer pId,
                                        @RequestParam("content")String content) throws ParseException {
        Map<String,Object> map = new HashMap<>();
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
        //检测没有敏感词直接插入
        if(!BadWordUtil.isContaintBadWord(content,2)) {
            evaluateService.addEvaluate(evaluate);
            Msg msg = new Msg(evaluate.getTitle(), "添加评论成功", content);
            map.put("message",msg);
            return map;
        }
        else{
            Msg msg = new Msg(evaluate.getTitle(), "添加评论，评论中含有敏感词！！！", null);
            map.put("message",msg);
            return map;
        }
    }


}
