package com.test;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.junit.Test;

import java.io.*;

public class CommonsCompress2 {

    @Test
    public void Test() {
        long startInt = System.currentTimeMillis();
        String zipFilePath = "文件输出/NO1PPTS/丝绸之路驼队背景的企业培训PPT模板.zip";
        File zipFile = new File(zipFilePath);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //zip文件输入流
        ZipArchiveInputStream zipArchiveInputStream = null;
        ArchiveEntry archiveEntry = null;
        try {
            inputStream = new FileInputStream(new File(zipFilePath));
            zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "GBK");
            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //获取文件名
                String archiveEntryFileName = archiveEntry.getName();
//                archiveEntryFileName = String.valueOf(zipEncoding.encode(archiveEntryFileName));
                //构造解压后文件的存放路径
                String archiveEntryPath = "文件输出/unzip/" + archiveEntryFileName;
                byte[] content = new byte[(int) archiveEntry.getSize()];
                zipArchiveInputStream.read(content);
                //把解压出来的文件写到指定路径
                File entryFile = new File(archiveEntryPath);
                if (!entryFile.exists()) {
                    entryFile.getParentFile().mkdirs();
                }
                outputStream = new FileOutputStream(entryFile);
                outputStream.write(content);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
//          e.printStackTrace();
        } catch (IOException e) {
//          e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
//                  e.printStackTrace();
                }
            }
            if (null != zipArchiveInputStream) {
                try {
                    zipArchiveInputStream.close();
                } catch (IOException e) {
//                  e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
//                  e.printStackTrace();
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startInt) + "毫秒");
    }
}
