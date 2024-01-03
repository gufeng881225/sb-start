package com.sb.staging.main;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 说明：java压缩成zip
 * 作者：FH Admin
 * from：fhadmin.cn
 */
public class FileZip {
    public static void main(String[] temp) throws UnsupportedEncodingException {
        byte[] data1 = fileConvertToByteArray(new File("D://EMP_DATA_EIS.csv.zip"));
        byte[] data2 = fileConvertToByteArray(new File("D://ORG_DATA.csv.zip"));

        System.out.println("EMP_DATA_EIS.csv.zip:");
        System.out.println(Base64.getEncoder().encodeToString(data1));
        System.out.println();
        System.out.println();
        System.out.println("ORG_DATA.csv.zip:");
        System.out.println(Base64.getEncoder().encodeToString(data2));
    }

    private static byte[] fileConvertToByteArray(File file) {
        byte[] data;
        try (InputStream inputStream = Files.newInputStream(file.toPath());
             BufferedInputStream bis = new BufferedInputStream(inputStream);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            inputStream.close();
            bis.close();
            data = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
