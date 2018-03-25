package com.parse.ppt.poi.service.common.download;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个Service 用来下载PPT文件到浏览器-No1PPT / PoiPPT
 *
 * @author Jupiter
 * @date 2018/3/25
 */
@Service
public interface PptDownloadService {
    /**
     * 根据前端传递过来的 pptID，获取到PPT的相关信息
     *
     * @param pptId    数据库中当前PPT的ID
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String pptDownloader(String pptId, HttpServletRequest request, HttpServletResponse response);
}
