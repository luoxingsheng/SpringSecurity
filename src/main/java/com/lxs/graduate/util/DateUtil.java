package com.lxs.graduate.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    public String getCurrentDate() throws ParseException {
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        return nowTime;
    }

    public static void main(String[] args) throws ParseException {
        DateUtil dateUtil=new DateUtil();
        System.out.println(dateUtil.getCurrentDate());
    }
}
