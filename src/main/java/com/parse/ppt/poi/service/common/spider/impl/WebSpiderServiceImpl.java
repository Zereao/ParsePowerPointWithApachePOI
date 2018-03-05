package com.parse.ppt.poi.service.common.spider.impl;

import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author Jupiter
 * @date 2018/03/05/13:55
 */
@Service
public class WebSpiderServiceImpl implements WebSpiderService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptService no1PptService;

    @Autowired
    public WebSpiderServiceImpl(No1PptService no1PptService) {
        this.no1PptService = no1PptService;
    }

    @Override
    public List<No1PPT> pptFileSpider(String pageIndex) {
        logger.info("WebSpiderServiceImpl.pptFileSpider   ------->  start! " +
                "   pageIndex = " + pageIndex);
        try {
            List<No1PPT> resultMapList = new ArrayList<>();
            String url = "http://www.1ppt.com/moban/ppt_moban_" + pageIndex.trim() + ".html";
            Document doc = Jsoup.connect(url).get();
            // <div class="w center mt4"> ... </div>  取得这个 <div> 中间的所有元素
            Elements fatherDivElements = doc.getElementsByClass("w center mt4");
            // 记住下面这种写法
            Elements ulElements = fatherDivElements.select("ul[class^=tplist]");
            Elements liElements = ulElements.select("li");
            //  这里这个result 无实用意义
            String result = null;
            for (Element eachElement : liElements) {
                // 取<li>标签的子标签<a>下面的<img>标签的内容
                Elements imgSrc = eachElement.select("a").select("img");
                String imgSrcInfo = imgSrc.toString();
                String[] infoArray = imgSrcInfo.split(" ");
                // 资源缩略图链接
                String srcImgUrl = infoArray[1].trim().replace("src=\"", "").replace("\"", "");
                // 资源介绍
                String srcDescription = infoArray[2].trim().replace("alt=\"", "").replace("\">", "");
                // 取<li>标签下的子标签<h2>下面的<a>标签的内容
                Elements downloadPage = eachElement.select("h2").select("a");
                String[] urlArray = downloadPage.toString().split(" ");
                // 操作字符串得到该PPT下载页面的链接
                String downloadPageUrl = "http://www.1ppt.com" + urlArray[1].trim().replace("href=\"", "").replace("\"", "");
                String downloadUrl = getDownloadUrl(downloadPageUrl);
                No1PPT no1PPT = new No1PPT(srcDescription, srcImgUrl, downloadUrl);
                result = no1PptService.addNo1PPT(no1PPT);
                resultMapList.add(no1PPT);
            }
            logger.info("WebSpiderServiceImpl.pptFileSpider   ------->  end!  result = " + result);
            return resultMapList;
        } catch (Exception e) {
            logger.error("WebSpiderServiceImpl.pptFileSpider   ------->  ERROR!" + e.getMessage());
        }
        return null;
    }


    /**
     * 私有方法
     * <p>
     * 根据下载页面的链接，通过解析，得到PPT文件的下载链接
     *
     * @param downloadPageUrl 下载页面的链接
     * @return 下载链接
     */
    private String getDownloadUrl(String downloadPageUrl) throws IOException {
        Document doc = Jsoup.connect(downloadPageUrl).get();
        Elements urlInfo = doc.getElementsByClass("downurllist").select("li").select("a");
        String[] a = urlInfo.toString().substring(9).split("\"");
        return a[0];
    }
}
