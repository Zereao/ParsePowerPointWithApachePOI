package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/03/05/13:54
 */
@Service
public interface SpiderService {
    /**
     * 使用爬虫技术，从 第1PPT  http://www.1ppt.com/ 网站上直接爬取现成的PPT模板
     * <p>
     * 仅做个人研究使用，若有侵权，请联系我，我将立即删除。
     *
     * @param pageIndex 第1PPT的免费模板页面的 页码信息，每一页若数据放满，则有20条PPT信息
     * @return resultMapList-即保存了对应于 pageIndex 的 第1PPT 网站页面上的 所有 PPT 对象的一个List
     */
    List<No1PPT> pptFileSpider(String pageIndex);

    /**
     * 使用爬虫技术，从百度爬取图片，用作模板的内容
     *
     * @param pageIndex 需要爬取的页数，不是爬取第几页，而是总共爬取几页
     * @param keyWord   搜索图片的关键字
     * @return 包含 pageIndex * 30 张图片的下载链接的一个List
     */
    List<String> BaiduPicSpider(int pageIndex, String keyWord);
}
