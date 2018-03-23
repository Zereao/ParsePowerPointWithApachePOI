package com.parse.ppt.poi.common;

import java.io.File;

public class PathUtil {
    private static String getAbstractProjectPath() {
        return PathUtil.class.getResource("/").getPath().replaceAll("^(/)|(target/ParsePowerPointWithApachePOI/WEB-INF/classes/)$", "");
    }

    /**
     * @return ${AbstractProjectPath}/ZeroFilesOutput/
     */
    public static String getAbsolutelyLocalResourcePath() {
        return getAbstractProjectPath() + "/ZeroFilesOutput/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbstractProjectPath}/ZeroFilesOutput/NO1PPTS/${no1pptId}/
     */
    public static String getAbsolutelyPptPath(String no1pptId) {
        return getAbstractProjectPath() + "/ZeroFilesOutput/NO1PPTS/" + no1pptId + "/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbstractProjectPath}/ZeroFilesOutput/PPT2IMG/${no1pptId}/
     */
    public static String getAbsolutelyPpt2imgPath(String no1pptId) {
        return getAbstractProjectPath() + "/ZeroFilesOutput/PPT2IMG/" + no1pptId + "/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbstractProjectPath}/ZeroFilesOutput/ZIPEDPPT/
     */
    public static String getAbsolutelyZipedPptPath(String no1pptId) {
        return getAbstractProjectPath() + "/ZeroFilesOutput/ZIPEDPPT/" + no1pptId + "/";
    }

    /**
     * @return ${AbstractProjectPath}/ZeroFilesOutput/BAIDUIMGS/
     */
    public static String getAbsolutelyBaiduImgPath() {
        return getAbstractProjectPath() + "/ZeroFilesOutput/BAIDUIMGS/";
    }

    /**
     * 根据 pptId获取到对应目录下的第一个 ppt/pptx 文件，如果文件不存在则返回Null
     *
     * @param pptId pptId
     * @return ppt的File对象
     */
    public static File getPptFile(String pptId) {
        File[] files = new File(getAbsolutelyPptPath(pptId)).listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.toLowerCase().contains(".ppt") || fileName.toLowerCase().contains(".pptx")) {
                    return file;
                }
            }
        }
        return null;
    }
}
