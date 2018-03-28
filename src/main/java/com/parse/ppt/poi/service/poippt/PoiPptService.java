package com.parse.ppt.poi.service.poippt;

import com.parse.ppt.poi.entity.PoiPPT;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface PoiPptService {
    /**
     * 拆分关键词，并将其放入List中返回
     *
     * @param keywords 关键词，以逗号隔开
     * @return keywordList
     */
    List<String> spiltKeywords(String keywords);

    /**
     * 根据关键词获取到对应的PoiPPT对象<br>
     * 为了保证用户体验只读取16条
     *
     * @param keywords 关键词，以逗号隔开
     * @param session  HttpSession对象
     * @return 对应的PoiPPT对象
     */
    PoiPPT getPoiPptByKeyword(String keywords, HttpSession session);


}
