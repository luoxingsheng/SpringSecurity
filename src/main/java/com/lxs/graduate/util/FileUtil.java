package com.lxs.graduate.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        System.out.println("上传");
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }


        public static void main(String[] args) {
            String value = "lxs.png";
            // 注意要加\\,要不出不来,yeah
            String[] names = value.split("\\.");
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[1]);
            }
        }


}
