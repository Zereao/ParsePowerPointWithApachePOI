package com.parse.ppt.poi.service.task;

import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.InitializeService;
import com.parse.ppt.poi.service.common.SpiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jupiter
 * @version 2018/06/01/11:06
 */
@Service
//@EnableScheduling
public class TaskService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final No1PptDao no1PptDao;
    private final SpiderService spiderService;

    @Autowired
    public TaskService(No1PptDao no1PptDao, SpiderService spiderService) {
        this.no1PptDao = no1PptDao;
        this.spiderService = spiderService;
        logger.info("#####################################          TaskService             ############################################");
    }

    //    @Scheduled(cron = "*/1 * * * * ?")
    public void grabNo1pptInfo() {
        for (int i = 1; i < 101; i++) {
            List<No1PPT> no1PPTList = spiderService.pptFileSpider(String.valueOf(i));
            if (no1PPTList == null || no1PPTList.size() == 0) {
                continue;
            }
            logger.info("no1PPTList.size() = {}", no1PPTList.size());
            for (No1PPT no1PPT : no1PPTList) {
                no1PptDao.addNo1PPT(no1PPT);
            }
        }
        try {
            TimeUnit.HOURS.sleep(12L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
