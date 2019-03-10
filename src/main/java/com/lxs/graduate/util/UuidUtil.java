package com.lxs.graduate.util;

import java.util.UUID;

public class UuidUtil {

    public String getUuid(){
        UUID id= UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }


    public static void main(String[] args) {
        UuidUtil uuidUtil=new UuidUtil();
        System.out.println(uuidUtil.getUuid());

    }

}
