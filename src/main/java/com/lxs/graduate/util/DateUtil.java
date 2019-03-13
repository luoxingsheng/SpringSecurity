package com.lxs.graduate.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
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

    public String getDate(){
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyMMdd");
        String nowss = sdf.format(date);
        return nowss;
    }

    public static void main(String[] args) throws ParseException {
    }
}
