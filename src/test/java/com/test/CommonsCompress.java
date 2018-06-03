package com.test;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

import java.io.*;

public class CommonsCompress {
    @Test
    public void unzipTest() {

        String zipFilePath = "文件输出/NO1PPTS/丝绸之路驼队背景的企业培训PPT模板.zip";
        String unzipFilePath = "文件输出/NO1PPTS/unzip/";
        try {
            FileInputStream in = new FileInputStream(zipFilePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            ZipArchiveInputStream inputStream = new ZipArchiveInputStream(bufferedInputStream);
            File pathFile = new File(unzipFilePath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipArchiveEntry entry = null;
            while ((entry = inputStream.getNextZipEntry()) != null) {
                if (entry.isDirectory()) {
                    File directory = new File(unzipFilePath, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {

                        System.out.println("5555555555555555555" + entry.getName());
                        os = new BufferedOutputStream(new FileOutputStream(new File(unzipFilePath, entry.getName())));
                        //输出文件路径信息
                        System.out.println("解压文件的当前路径为:{}" + unzipFilePath + entry.getName());
                        IOUtils.copy(inputStream, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
            System.out.println("22222222222222222222222");
            final File[] files = pathFile.listFiles();
            if (files != null && files.length == 1 && files[0].isDirectory()) {
                // 说明只有一个文件夹
//                FileUtils.copyDirectory(files[0], pathFile);
                //免得删除错误， 删除的文件必须在/data/demand/目录下。
                boolean isValid = files[0].getPath().contains("/data/www/");
                if (isValid) {
//                    FileUtils.forceDelete(files[0]);
                }
            }
            System.out.println("******************解压完毕********************");

        } catch (Exception e) {
            System.out.println("[unzip] 解压zip文件出错     " + e.getMessage());
        }
    }
}
