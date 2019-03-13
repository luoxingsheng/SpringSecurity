package com.lxs.graduate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class IdUtil {

    @Autowired
    DateUtil dateUtil;


    public Long getOrderId(Integer userId,Integer pId) throws ParseException {
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( "HHmmss");
        String now = sdf.format(date);
        String sId=now+String.valueOf(userId)+String.valueOf(pId);
        Long id=Long.parseLong(sId);
        return id;
    }

    public static void main(String[] args) {
    }
}
