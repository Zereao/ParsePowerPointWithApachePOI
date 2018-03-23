package com.parse.ppt.poi.service.common.download;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 这个Service 用于从网络上下载资源到本地磁盘，一般运行在Test方法中
 *
 * @author Jupiter
 * @date 2018/03/08/16:46
 */
@Service
public interface FileDownloadService {
    /**
     * 从 第一PPT 网站上把 no1ppt对象对应的包含PPT文件的压缩包下载到本地
     *
     * @param no1PPT 需要下载的no1ppt对象
     * @return ReturnCode 返回码
     */
    String downloadZipedNo1PPT(No1PPT no1PPT);

    /**
     * 从 第一PPT 网站上批量把 包含PPT文件的压缩包下载到本地
     *
     * @param no1PPTList 包含no1ppt相关信息的List
     * @return ReturnCode 返回码
     */
    String downloadZipedNo1ppts(List<No1PPT> no1PPTList);

    /**
     * 使用多线程从 第一PPT 网站上批量把 包含PPT文件的压缩包下载到本地
     *
     * @param no1PPTList 包含no1ppt相关信息的List
     * @return ReturnCode 返回码
     */
    String downloadZipedNo1pptsSync(List<No1PPT> no1PPTList);

    /**
     * 使用多线程从 第一PPT 网站上批量把1700条包含PPT文件的压缩包下载到本地
     *
     * @return ReturnCode 返回码
     */
    String downloadZipedNo1pptsSync();

    /**
     * 从 第一PPT 网站上把 no1ppt对象对应的首页图片下载到本地
     *
     * @param no1PPT no1ppt对象
     * @return ReturnCode 返回码
     */
    String downloadPptImg(No1PPT no1PPT);

    /**
     * 从 第一PPT 网站上批量把 no1ppt对象对应的首页图片下载到本地
     *
     * @param no1PPTList 包含no1ppt相关信息的List
     * @return ReturnCode 返回码
     */
    String downloadPptImgs(List<No1PPT> no1PPTList);

    /**
     * 使用多线程从 第一PPT 网站上批量把 no1ppt对象对应的首页图片下载到本地
     *
     * @param no1PPTList 包含no1ppt相关信息的List
     * @return ReturnCode 返回码
     */
    String downloadPptImgsSync(List<No1PPT> no1PPTList);

    /**
     * 使用多线程从 第一PPT 网站上批量把1700条no1ppt对象对应的首页图片下载到本地
     *
     * @return ReturnCode 返回码
     */
    String downloadPptImgssSync();



    /**
     * 根据爬取到的百度图片的下载链接信息，下载爬取到的图片到本地临时文件夹
     *
     * @param imgUrlList 爬取到的百度图片的下载链接信息
     * @return 返回码-ReturnCode
     */
    String downloadBaiduImg(List<String> imgUrlList);

}
