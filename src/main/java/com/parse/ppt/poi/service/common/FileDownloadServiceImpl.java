package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Jupiter
 * @version 2018/03/08/16:45
 */
//@Service
@Deprecated
public class FileDownloadServiceImpl implements FileDownloadService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

    private final No1PptDao no1PptDao;

    @Autowired
    public FileDownloadServiceImpl(No1PptDao no1PptDao) {
        this.no1PptDao = no1PptDao;
    }

    @Override
    public String downloadZipedNo1PPT(No1PPT no1PPT) {
        logger.info("------->  start!   no1PPT = {}", no1PPT);
        try {
            zipedPPTDownloader(no1PPT);
            logger.info("------->  end!   result = {}", ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return FAILED");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadZipedNo1ppts(List<No1PPT> no1PPTList) {
        //        已知数据库中存在1720条数据
        try {
            logger.info("------->  start!   no1PPTList = {}", no1PPTList);
            for (No1PPT no1PPT : no1PPTList) {
                zipedPPTDownloader(no1PPT);
            }
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return FAILED");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadZipedNo1pptsSync() {
        // 已知数据库中存在1720条数据，这里只下载1700条
        try {
            logger.info("------->  start!");
            List<No1PPT> no1PPTList1 = no1PptDao.getNo1PPT(0, 340);
            List<No1PPT> no1PPTList2 = no1PptDao.getNo1PPT(340, 340 * 2);
            List<No1PPT> no1PPTList3 = no1PptDao.getNo1PPT(340 * 2, 340 * 3);
            List<No1PPT> no1PPTList4 = no1PptDao.getNo1PPT(340 * 3, 340 * 4);
            List<No1PPT> no1PPTList5 = no1PptDao.getNo1PPT(340 * 4, 340 * 5);
            Thread thread1 = new Thread(() -> downloadZipedNo1ppts(no1PPTList1));
            Thread thread2 = new Thread(() -> downloadZipedNo1ppts(no1PPTList2));
            Thread thread3 = new Thread(() -> downloadZipedNo1ppts(no1PPTList3));
            Thread thread4 = new Thread(() -> downloadZipedNo1ppts(no1PPTList4));
            Thread thread5 = new Thread(() -> downloadZipedNo1ppts(no1PPTList5));
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return FAILED");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadPptImg(No1PPT no1PPT) {
        logger.info("------->  start!" +
                "   no1PPT = " + no1PPT);
        try {
            int pptId = no1PPT.getId();
            String imgUrl = no1PPT.getImgUrl();
            final String pptPath = PathUtil.getNo1PptPath(String.valueOf(pptId)) + pptId + ".png";
            boolean isDownloadSuccess = imgDownloader(imgUrl, pptPath);
            logger.info("------->  end!  result = " + isDownloadSuccess);
            if (isDownloadSuccess) {
                return ReturnCode.SUCCESS;
            }
            return ReturnCode.FAILED;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadPptImgs(List<No1PPT> no1PPTList) {
        logger.info("------->  start!" +
                "   no1PPTList = " + no1PPTList);
        try {
            for (No1PPT no1PPT : no1PPTList) {
                int pptId = no1PPT.getId();
                String imgUrl = no1PPT.getImgUrl();
                // count_name 作为文件名的数量标记，文件名最终为  1.jpg 2.png 3.jpg 4.gif 等
                final String pptPath = PathUtil.getNo1PptPath(String.valueOf(pptId)) + pptId + ".png";
                boolean isDownloadSuccess = imgDownloader(imgUrl, pptPath);
                if (!isDownloadSuccess) {
                    logger.error("------->  ERROR!  no1PPT = " + no1PPT);
                }
            }
            logger.info("------->  end!  result = SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadPptImgsSync(List<No1PPT> no1PPTList) {
        return null;
    }

    @Override
    public String downloadPptImgssSync() {
        // 已知数据库中存在1720条数据，这里只下载1700条
        try {
            logger.info("------->  start!");
            List<No1PPT> no1PPTList1 = no1PptDao.getNo1PPT(0, 340);
            List<No1PPT> no1PPTList2 = no1PptDao.getNo1PPT(340, 340 * 2);
            List<No1PPT> no1PPTList3 = no1PptDao.getNo1PPT(340 * 2, 340 * 3);
            List<No1PPT> no1PPTList4 = no1PptDao.getNo1PPT(340 * 3, 340 * 4);
            List<No1PPT> no1PPTList5 = no1PptDao.getNo1PPT(340 * 4, 340 * 5);
            Thread thread1 = new Thread(() -> downloadPptImgs(no1PPTList1));
            Thread thread2 = new Thread(() -> downloadPptImgs(no1PPTList2));
            Thread thread3 = new Thread(() -> downloadPptImgs(no1PPTList3));
            Thread thread4 = new Thread(() -> downloadPptImgs(no1PPTList4));
            Thread thread5 = new Thread(() -> downloadPptImgs(no1PPTList5));
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return FAILED");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadBaiduImg(List<String> imgUrlList) {
        logger.info("------->  start!" +
                "   imgUrlList = " + imgUrlList);
        try {
            // count_name 作为文件名的数量标记，文件名最终为  1.jpg 2.png 3.jpg 4.gif 等
            int count_name = 1;
            final String BASE_PATH = PathUtil.getBaiduImgPath();
            File tempFile = new File(BASE_PATH);
            if (!tempFile.exists()) {
                boolean isCreate = tempFile.mkdir();
            }
            for (String imgUrl : imgUrlList) {
                String fileName = null;
                // 最后格式中 . 的位置  例如 123.png ，则 index = 3
                int index = 0;
                // 错误的连接,则直接跳过
                if ((index = imgUrl.lastIndexOf(".")) == -1) {
                    continue;
                }
                // 下载所有格式，匹配 .png 和 .gif 的扩展名
                boolean allExt = ".png".equals(imgUrl.substring(index).toLowerCase()) ||
                        ".gif".equals(imgUrl.substring(index).toLowerCase());
                if (allExt) {
                    fileName = count_name + imgUrl.substring(index);
                } else {
                    fileName = count_name + ".jpg";
                }
                String filePath = BASE_PATH + fileName;
                boolean isDownloadSuccess = baiduImgDownloader(imgUrl, filePath);
                if (isDownloadSuccess) {
                    count_name++;
                }
            }
            logger.info("------->  end!  result = SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    /**
     * 私有的no1ppt-压缩包的下载方法
     *
     * @param no1PPT 文件保存路径
     */
    private void zipedPPTDownloader(No1PPT no1PPT) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   no1PPT = {}", no1PPT);
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            int pptId = no1PPT.getId();
            String zipedPPTPath = PathUtil.getZipedPPTPath();
            String downloadUrl = no1PPT.getDownloadUrl().trim();
            String downloadPageUrl = no1PPT.getDownloadPageUrl();
            String pptExt = downloadUrl.substring(downloadUrl.lastIndexOf("."));
            HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("referer", downloadPageUrl);
            inputStream = conn.getInputStream();
            // 创建输出流  最后的文件名  例子：  E:/ParsePowerPointWithApachePOI/FilesHub/ZipedPPT/1.rar
            outputStream = new FileOutputStream(zipedPPTPath + pptId + pptExt);
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区中
            while ((len = inputStream.read(buffer)) > 0) {
                // 输出缓冲区内容到浏览器，实现文件下载
                outputStream.write(buffer, 0, len);
            }
            if (logger.isDebugEnabled()) {
                logger.info("------->  end!   result = {}", ReturnCode.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        } finally {
            try {
                // 关闭输出流
                if (outputStream != null) {
                    outputStream.close();
                }
                // 关闭文件流
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("------->  finally block ERROR!");
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 私有的图片下载方法
     *
     * @param imgUrl          图片的下载地址
     * @param fileNameAndPath 包含文件名的文件路径    例如： E:/ParsePowerPointWithApachePOI/FilesHub/PPT2Img/1/1.png
     * @return 返回值，是否下载成功
     */
    private boolean imgDownloader(String imgUrl, String fileNameAndPath) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   imgUrl = {}   fileNameAndPath = {}", imgUrl, fileNameAndPath);
        }
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 文件路径
            URL url = new URL(imgUrl);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection huc = (HttpURLConnection) urlConnection;
            // 设置 HttpsURLConnection 的相关属性
            huc.setDoOutput(true);
            huc.setRequestProperty("User-Agent", USER_AGENT);
            huc.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
            huc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            huc.setRequestProperty("Connection", "keep-alive");
            bufferedInputStream = new BufferedInputStream(huc.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileNameAndPath));
            byte[] temp = new byte[819200];
            //  BufferedInputStream.read()  返回读取的比特数长度；如果返回-1，表示文件结束
            int len = 0;
            while ((len = bufferedInputStream.read(temp)) != -1) {
                bufferedOutputStream.write(temp, 0, len);
                bufferedOutputStream.flush();
            }
            if (logger.isDebugEnabled()) {
                logger.info("------->  end!   SUCCESS");
            }
            return true;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 false ");
            logger.error(e.getMessage());
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                logger.error("------->   finally block ERROR!");
                logger.error(e.getMessage());
            }
        }
        return false;
    }


    /**
     * 私有的百度图片下载方法，实现逻辑就是 调用imgDownloader()方法，下载图片，如果图片下载错误，则删掉错误图片。
     *
     * @param imgUrl          图片的下载地址
     * @param fileNameAndPath 包含文件名的文件路径    例如： E:\ParsePowerPointWithApachePOI\ZeroFilesOutput\PPT2IMG\1\1.png
     * @return 返回值，是否下载成功
     */
    private boolean baiduImgDownloader(String imgUrl, String fileNameAndPath) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!    imgUrl = {}   fileNameAndPath = {}", imgUrl, fileNameAndPath);
        }
        try {
            boolean downloadSuccess = imgDownloader(imgUrl, fileNameAndPath);
            if (!downloadSuccess) {
                logger.warn("------->  ERROR!  即将删除掉错误图片文件!     filePath = {}", fileNameAndPath);
                File errorFile = new File(fileNameAndPath);
                boolean isDeleted = false;
                if (errorFile.exists()) {
                    isDeleted = errorFile.delete();
                }
                logger.warn("------->  end! 错误文件已经删除 = " + isDeleted);
                return false;
            }
            // 下载成功，返回true
            return true;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 false ");
            logger.error(e.getMessage());
        }
        return false;
    }


}
