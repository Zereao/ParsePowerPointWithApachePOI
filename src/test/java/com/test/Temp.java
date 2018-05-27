package com.test;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.service.common.EncryptServiceImpl;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Temp {
    @Test
    public void test() {
        EncryptServiceImpl demo1 = new EncryptServiceImpl();
        String pri = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAioPDAXnRkJOUbeCnwFvmkvqtdvPqU23EuNYatdJWcV8aCOOj6O/ck+74qft1BgPfYqjbV6i2354uInqXYyR1lwIDAQABAj903reqhx5K3jpeey8PHnttiBTUSPltazCQxBXFU7Ob81X7IJOnYn5HqP39+oQ5r67+GdESPKfgT9qAqvBI8sECIQD90VNTUSbuSpETaez4Z2Lamyt70HndUYa5e3/mEeJVewIhAIu0pPy0RmLVCWzGBGcLbPp+Q4Zvam7BF8amXIpDK4+VAiEAsczx9vpxMBoSGKxqMPSTfhmW02wOAih7HPHTfYJnr4MCIEJAVWl9eE+QJZsOwLG5KmIUFKOhHIEPUt4isInk1riNAiBriq0myHm4DN5onoZ7SZK4usf3Tmr48efJ9eOCqz97iA==";
        String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIqDwwF50ZCTlG3gp8Bb5pL6rXbz6lNtxLjWGrXSVnFfGgjjo+jv3JPu+Kn7dQYD32Ko21eott+eLiJ6l2MkdZcCAwEAAQ==";
        String d = "W5dUTPuGh1jzJzNAMLK9nk9bLfs7cA9VlZIEOLNKUfL6qphbxDyknB8u7N4X9FEyHu/yMOdDkDn6AdZB4MkM";
        String a = demo1.contentDecrypter(pri, d);
        System.out.println(a);
    }

    @Test
    public void test1() {
        EncryptServiceImpl demo1 = new EncryptServiceImpl();
        String pri = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAjlPXzW2O8Vj5BpScM4eu/LJ7dWPUaRhe7MATdKX7wgZjzzfC3K+j2sBB/5gUzl0fy3+/pJAhm0xSjhXWCqKA2QIDAQABAkB/BFYn7+lMfCGpfMC1wBYrUEGbrt7md6hnUrdKN8MpEF1nwG6jVM9BLo7cPXb9Css9ykUdwwL1aTx2M8H4IGwBAiEA6Ka12i/BAGSq7yt85d8vW58RoMW+KUIiA5vSZtpN8VkCIQCcnIfH+uTiR9T42/4AJ5YvCnQNSIZyC7MVLR2777ObgQIhAN4zQyJVU69Ndlihn1NpOzD1lv+HfVyodhYeP/DN9X1ZAiBLbWhH/KJ4CTidkvbGhEpk5Zf3PdLi4pehv1TuyweqAQIhAMof1bwsbREHwFNnCARjqVj7HXXlZsDrzbhxEDmehsoL";
        String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI5T181tjvFY+QaUnDOHrvyye3Vj1GkYXuzAE3Sl+8IGY883wtyvo9rAQf+YFM5dH8t/v6SQIZtMUo4V1gqigNkCAwEAAQ==";
        String d = "RDaTBTp2IT00AhJjbXimVjXyfnkj8b1jzEzPdCDtE+vh2TcAzTVPKGqOYHrA55piVY/da43PjSnC3LBuXGs/LQ==";
        String a = demo1.contentEncrypter(pri, d);
        System.out.println(a);
    }

    @Test
    public void test2() {
        String zipFilePath = "文件输出/NO1PPTS/1.zip";
        String saveFileDir = "文件输出/unzip/";
        if (isEndsWithZip(zipFilePath)) {
            File file = new File(zipFilePath);
            if (file.exists()) {
                InputStream is = null;
                //can read Zip archives
                ZipArchiveInputStream zais = null;
                try {
                    is = new FileInputStream(file);
                    zais = new ZipArchiveInputStream(is, "GBK");
                    ArchiveEntry archiveEntry = null;
                    //把zip包中的每个文件读取出来
                    //然后把文件写到指定的文件夹
                    while ((archiveEntry = zais.getNextEntry()) != null) {
                        System.out.println(archiveEntry.getName());
                        if (archiveEntry.getName().toLowerCase().contains(".ppt") || archiveEntry.getName().toLowerCase().contains(".pptx")) {
                            //获取文件名
                            System.out.println("++++++++++++++++++++++++++");
                            String entryFileName = "1" + archiveEntry.getName().substring(archiveEntry.getName().length() - 5);
                            //构造解压出来的文件存放路径
                            String entryFilePath = saveFileDir + entryFileName;
                            byte[] content = new byte[(int) archiveEntry.getSize()];
                            zais.read(content);
                            OutputStream os = null;
                            try {
                                //把解压出来的文件写到指定路径
                                File entryFile = new File(entryFilePath);
                                os = new BufferedOutputStream(new FileOutputStream(entryFile));
                                os.write(content);
                            } catch (IOException e) {
                                throw new IOException(e);
                            } finally {
                                if (os != null) {
                                    os.flush();
                                    os.close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (zais != null) {
                            zais.close();
                        }
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 判断文件名是否以.zip为后缀
     *
     * @param fileName 需要判断的文件名
     * @return 是zip文件返回true, 否则返回false
     */
    private static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }

    @Test
    public void test3() {
        String path = "文件输出/NO1PPTS/2/奖牌领奖台.ppt";
        File file = new File(path);
        System.out.println(file.getParent());
        System.out.println(file.getParentFile().getParent());
        System.out.println(file.getParentFile().getParentFile().getParent());
    }

    @Test
    public void test4() {
        String path = PathUtil.getAbsoluteNo1PptPath("4");
        File file = new File(path);
        File[] files = file.listFiles();
        System.out.println(files.length);
    }

    @Test
    public void test5() {
        List<String> resultList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<String> result = new ArrayList<>();

        stringList.add("红色");
        stringList.add("蓝天");
        stringList.add("45");
        stringList.add("狐狸");
        stringList.add("啦啦");
        stringList.add("猪");
        stringList.add("小白");
        for (int i = 0; i < 4; i++) {
            result.addAll(resultList);
        }
        System.out.println("+++++++++++++++++++++++++");
        for (String s : result) {
            System.out.println(s);
        }

    }

    @Test
    public void combination() {
        String[] strArray = {"红色", "蓝天", "45", "狐狸", "啦啦", "猪", "小白"};
        char[][] c = new char[strArray.length][];
        int[] cnt = new int[strArray.length];
        int[] index = new int[strArray.length]; //进位用计数器
        Arrays.fill(index, 0); //初始为0
        for (int i = 0; i < strArray.length; i++) {
            c[i] = strArray[i].toCharArray(); //把多个字符的字符串拆分为多个字符
            cnt[i] = c[i].length; //每个位置的字符数
        }

        while (true) {
            for (int i = 0; i < index.length; i++) {
                System.out.print(c[i][index[i]]); //打印每个位置的字符
            }
            System.out.println();
            index[index.length - 1]++; //最后一个位置计数器增加
            for (int i = index.length - 1; i > 0; i--) { //判断是否发生进位
                if (index[i] == cnt[i]) { //如果某一个位置的计数器达到该位置的最大字符数
                    index[i] = 0; //该位置的计数器清0
                    index[i - 1]++; //进位，也就是某位置的前一位递增
                }
            }
            if (index[0] == cnt[0]) { //如果进位到头了，则退出while循环
                break;
            }
        }
    }

    public void combination(List<String> stringList, List<String> workSpace, int k, List<List<String>> resultList) {

        List<String> copyData;
        List<String> copyWorkSpace;

        if (workSpace.size() == k) {
            List<String> a = new ArrayList<>(workSpace);
            resultList.add(a);
            System.out.println();
        }

        for (int i = 0; i < stringList.size(); i++) {
            copyData = new ArrayList<>(stringList);
            copyWorkSpace = new ArrayList<>(workSpace);

            copyWorkSpace.add(copyData.get(i));
            for (int j = i; j >= 0; j--)
                copyData.remove(j);
            combination(copyData, copyWorkSpace, k, resultList);
        }

    }

    @Test
    public void main() {
        List<String> data = new ArrayList<>();
        data.add("红色");
        data.add("蓝天");
        data.add("45");
        data.add("狐狸");
        data.add("啦啦");
        data.add("猪");
        data.add("小白");
        List<List<String>> objectList = new ArrayList<>();
        for (int i = 1; i < data.size(); i++)
            combination(data, new ArrayList<>(), i, objectList);

        System.out.println("++++++++++++++++");
        int i = 1;
        for (List o : objectList) {
            System.out.println(o.toString() + "    " + i);
            i++;
        }
    }
}