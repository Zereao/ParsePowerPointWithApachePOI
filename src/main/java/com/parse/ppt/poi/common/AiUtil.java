package com.parse.ppt.poi.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法AI类，为整个项目挺算法逻辑支持
 *
 * @author Jupiter
 * @version 2018/05/30/17:12
 */
public class AiUtil {
    /**
     * 私有的 排列组合之组合 求值方法，从字符串List - stringList中取出 n 个 字符串进行组合
     *
     * @param stringList 字符串List
     * @param workSpace  工作空间List，直接传入一个 new ArrayList<>() 即可
     * @param k          选取个数
     * @param resultList 保存了结果的List
     */
    public static void combination(List<String> stringList, List<String> workSpace, int k, List<List<String>> resultList) {
        List<String> copyData;
        List<String> copyWorkSpace;
        if (workSpace.size() == k) {
            List<String> tempList = new ArrayList<>(workSpace);
            resultList.add(tempList);
        }
        for (int i = 0; i < stringList.size(); i++) {
            copyData = new ArrayList<>(stringList);
            copyWorkSpace = new ArrayList<>(workSpace);
            copyWorkSpace.add(copyData.get(i));
            for (int j = i; j >= 0; j--)
                copyData.remove(j);
            combination(copyData, copyWorkSpace, k, resultList);
        }
    }

}
