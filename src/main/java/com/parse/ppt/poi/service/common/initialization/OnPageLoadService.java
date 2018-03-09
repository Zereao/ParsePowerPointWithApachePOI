package com.parse.ppt.poi.service.common.initialization;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/10:45
 */
@Service
public interface OnPageLoadService {
    /**
     * 根据email获取用户对象
     *
     * @param session HttpSession对象
     * @return 包含了index.jsp 的一些初始化信息的一个JSONObject对象
     */
    JSONObject getInitializeInfo(HttpSession session);
}
