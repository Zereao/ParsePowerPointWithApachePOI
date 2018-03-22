package com.parse.ppt.poi.common;

public class PathUtil {
    public static String getAbstractProjectPath() {
        return PathUtil.class.getResource("/").getPath().replaceAll("^(/)|(target/ParsePowerPointWithApachePOI/WEB-INF/classes/)$", "");
    }

    public static String getAbstractPptPath(String no1pptId) {
        return getAbstractProjectPath() + "/文件输出/NO1PPTS/" + no1pptId + "/";
    }

    public static String getAbstractPpt2imgPath(String no1pptId) {
        return getAbstractProjectPath() + "/文件输出/PPT2IMG/" + no1pptId + "/";
    }
}
