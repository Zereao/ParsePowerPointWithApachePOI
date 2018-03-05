package com.parse.ppt.poi.service.common.spider.impl;

import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    @Override
    public List<Map<String, String>> pptFileSpider(String pageIndex) {
        logger.info("WebSpiderServiceImpl.pptFileSpider   ------->  start! " +
                "   pageIndex = " + pageIndex);
        try {
            List<Map<String, String>> resultMapList = new ArrayList<>();
            String url = "http://www.1ppt.com/moban/ppt_moban_" + pageIndex.trim() + ".html";
            Document doc = Jsoup.connect(url).get();
            // <div class="w center mt4"> ... </div>  取得这个 <div> 中间的所有元素
            Elements fatherDivElements = doc.getElementsByClass("w center mt4");
            // 记住下面这种写法
            Elements ulElements = fatherDivElements.select("ul[class^=tplist]");
            Elements liElements = ulElements.select("li");
            for (Element eachElement : liElements) {
                // 取<li>标签的子标签<a>下面的<img>标签的内容
                Elements imgSrc = eachElement.select("a").select("img");
                String imgSrcInfo = imgSrc.toString();
                String[] infoArray = imgSrcInfo.split(" ");
                // 资源缩略图链接
                String srcImgUrl = infoArray[1].trim().replace("src=\"", "").replace("\"", "");
                // 资源介绍
                String srcDescription = infoArray[2].trim().replace("alt=\"", "").replace("\"", "");
                // 取<li>标签下的子标签<h2>下面的<a>标签的内容
                Elements downloadPage = eachElement.select("h2").select("a");
                String[] urlArray = downloadPage.toString().split(" ");
                // 操作字符串得到该PPT下载页面的链接
                String downloadPageUrl = "http://www.1ppt.com" + urlArray[1].trim().replace("href=\"", "").replace("\"", "");
                String downloadUrl = getDownloadUrl(downloadPageUrl);
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("srcImgUrl", srcImgUrl);
                infoMap.put("srcDescription", srcDescription);
                infoMap.put("downloadUrl", downloadUrl);
                resultMapList.add(infoMap);
            }
            logger.info("WebSpiderServiceImpl.pptFileSpider   ------->  end!");
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
