package com.parse.ppt.poi.service.admin;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/14:04
 */
@Service
public interface AdministratorService {
    /**
     * 设置首页的文本显示
     *
     * @param essayTitle   文章名称
     * @param essayContent 文章内容
     * @param session      HttpSession对象
     * @return 返回包含 包含文章信息的一个 JSONObject
     */
    JSONObject setMainPageEssay(String essayTitle, String essayContent, HttpSession session);
}
