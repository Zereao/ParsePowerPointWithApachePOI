package com.test;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.service.common.encrypt.impl.EncryptServiceImpl;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.junit.Test;

import java.io.*;

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
}