package com.parse.ppt.poi.service.common.download.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import com.parse.ppt.poi.service.common.history.UserDownloadHistoryService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.download.FileDownloadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/03/08/16:45
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptService no1PptService;
    private final UserDownloadHistoryService userDownloadHistoryService;

    @Autowired
    public FileDownloadServiceImpl(No1PptService no1PptService, UserDownloadHistoryService userDownloadHistoryService) {
        this.no1PptService = no1PptService;
        this.userDownloadHistoryService = userDownloadHistoryService;
    }

    @Override
    public JSONArray getNo1PPT(int pageIndex) {
        logger.info("------->  start!" +
                "   pageIndex = " + pageIndex);
        try {
            List<Map<String, String>> resultMapList = no1PptService.getNo1PPT(pageIndex, 40);
            JSONArray jsonArray = new JSONArray();
            for (Map<String, String> map : resultMapList) {
                JSONObject jsonObject = new JSONObject();
                for (String key : map.keySet()) {
                    jsonObject.put(key, map.get(key));
                }
                jsonArray.add(jsonObject);
            }
            logger.info("------->  end!  SUCCESS");
            return jsonArray;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String downloadNo1PPT(int pptId, HttpSession session, HttpServletResponse response) {
        logger.info("------->  start!" +
                "   pptId = " + pptId);
        try {
            String result = no1PptService.downloadNo1PPT(pptId, response);
            boolean addUserDownloadHistory = result.equals(ReturnCode.SUCCESS) && session.getAttribute("user") != null;
            if (addUserDownloadHistory) {
                User user = (User) session.getAttribute("user");
                UserDownloadHistory userDownloadHistory = new UserDownloadHistory(user.getEmail(), pptId);
                result = userDownloadHistoryService.addDownloadHistory(userDownloadHistory);
            }
            logger.info("------->  end!  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
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
            final String BASE_PATH = "文件输出/百度图片爬取/";
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
                boolean isDownloadSuccess = downloadImg(imgUrl, filePath);
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
     * 私有的图片下载方法
     *
     * @param imgUrl   图片的下载地址
     * @param filePath 文件保存路径
     * @return 返回值，是否下载成功
     */
    private boolean downloadImg(String imgUrl, String filePath) {
        logger.info("------->  start!" +
                "   imgUrl = " + imgUrl);
        try {
            final int BUFFERSIZE = 819200;
            // 文件路径
            URL url = new URL(imgUrl);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection huc = (HttpURLConnection) urlConnection;
            // 设置 HttpsURLConnection 的相关属性
            huc.setDoOutput(true);
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
            huc.setRequestProperty("User-Agent", userAgent);
            huc.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
            huc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            huc.setRequestProperty("Connection", "keep-alive");
            BufferedInputStream bis = new BufferedInputStream(huc.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] temp = new byte[BUFFERSIZE];
            //  BufferedInputStream.read()  返回读取的比特数长度；如果返回-1，表示文件结束
            int count = 0;
            while ((count = bis.read(temp)) != -1) {
                bos.write(temp, 0, count);
                bos.flush();
            }
            logger.info("------->  end!   SUCCESS");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.warn("------->  ERROR!  即将删除掉错误图片文件!" +
                    "   filePath = " + filePath);
            File errorFile = new File(filePath);
            boolean isDeleted = false;
            if (errorFile.exists()) {
                isDeleted = errorFile.delete();
            }
            logger.warn("------->  end! 错误文件已经删除 = " + isDeleted);
            return false;
        }
    }


}
