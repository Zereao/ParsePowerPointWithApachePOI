package com.parse.ppt.poi.service.common.spider;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/03/05/13:54
 */
@Service
public interface WebSpiderService {
    /**
     * 使用爬虫技术，从 第1PPT  http://www.1ppt.com/ 网站上直接爬取现成的PPT模板
     * <p>
     * 仅做个人研究使用，若有侵权，请联系我，我将立即删除。
     *
     * @param pageIndex 第1PPT的免费模板页面的 页码信息
     * @return resultMapList
     */
    List<No1PPT> pptFileSpider(String pageIndex);
}
