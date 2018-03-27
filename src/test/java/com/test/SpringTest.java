package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.common.ocr.OcrService;
import com.parse.ppt.poi.service.common.poi.service.PoiService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import com.parse.ppt.poi.service.common.download.FileDownloadService;
import com.parse.ppt.poi.service.common.poi.operate.xslf.PptxOperateService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jupiter
 * @date 2018/03/01/12:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test_spring/spring.xml",
        "classpath:test_spring/spring-mail.xml",
//        "classpath:test_spring/spring-mvc.xml",
        "classpath:test_spring/spring-mybatis.xml",
        "classpath:test_spring/spring-redis.xml"})
public class SpringTest {

    @Autowired
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptService no1PptService;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private PptxOperateService pptxOperateService;
    @Autowired
    private No1PptDao no1PptDao;
    @Autowired
    private OcrService ocrService;
    @Autowired
    private PoiService poiService;

    private String result = null;

    @Test
    public void test1() throws InterruptedException {
        result = fileDownloadService.downloadPptImgssSync();
        Thread.sleep(24 * 3600 * 1000);
    }

    @Test
    public void testU() {
        result = no1PptService.ppt2img("7");

    }

    @Test
    public void test3() {
        String imgPath = "ZeroFilesOutput/baiduImgs/14.png";
        System.out.println("+++++++++++++++++++++++++++++++++++");
        List<String> result = null;
//        result = ocrService.getWordsWithBaiduOCR(imgPath);
        result = ocrService.getWordsWithTencentOCR(imgPath);
        System.out.println(result);
    }

    @Test
    public void test4() {
        List<No1PPT> result = no1PptDao.getNo1PPTByKeyWordFuzzy("红色");
        for (No1PPT no1PPT : result) {
            System.out.println(no1PPT.getSrcDescription());
        }
    }

    @Test
    public void test5() {
        List<String> keywordsList = new ArrayList<>();
        keywordsList.add("红色");
        keywordsList.add("运动");
        keywordsList.add("艺术");
        keywordsList.add("简洁");
        keywordsList.add("方块");

        Set<No1PPT> no1PPTSet = no1PptService.getNo1PPTByKeyWordsRelevancy(keywordsList);
        List<No1PPT> listOf20 = new ArrayList<>();
        int index = 0;
        for (No1PPT no1PPT : no1PPTSet) {
            if (index < 20) {
                listOf20.add(no1PPT);
            }
            index++;
            if (index > 20) {
                break;
            }
        }


        System.out.println("________________________________________________________________________________");
        System.out.println(listOf20);
        System.out.println("________________________________________________________________________________");

        int no1pptNum = listOf20.size();
        System.out.println("________________________________________________________________________________");

        System.out.println("no1pptNum = " + no1pptNum);
        List<No1PPT> list1 = new ArrayList<>();
        List<No1PPT> list2 = new ArrayList<>();
        List<No1PPT> list3 = new ArrayList<>();
        List<No1PPT> list4 = new ArrayList<>();
        int spilt = no1pptNum / 4;
        int index1 = 0;
        List<Map<No1PPT, int[]>> resultList = new ArrayList<>();
        for (No1PPT no1PPT : listOf20) {
            if (index < spilt) {
                list1.add(no1PPT);
                index++;
            } else if (index >= spilt && index < spilt * 2) {
                list2.add(no1PPT);
                index++;
            } else if (index >= spilt * 2 && index < spilt * 3) {
                list3.add(no1PPT);
                index++;
            } else {
                list4.add(no1PPT);
                index++;
            }
        }

        poiService.selectPPT(list1, 7);
        System.out.println("________________________________________________________________________________");

        poiService.selectPPT(list2, 7);
        System.out.println("________________________________________________________________________________");

        poiService.selectPPT(list3, 7);
        System.out.println("________________________________________________________________________________");

        poiService.selectPPT(list4, 7);

//        List<Map<No1PPT, int[]>> resultList1 = poiService.selectPPTSync(listOf20, 7);
//        String result = poiService.rebuildPPT(resultList);

    }

    @Test
    public void test6() {
        No1PPT no1PPT = no1PptDao.getNo1PPTById(1);
        int[] a = new int[]{5, 11};
        poiService.rebuildPPT(no1PPT, a);
    }
}
