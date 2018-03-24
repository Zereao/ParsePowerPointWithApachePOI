package com.parse.ppt.poi.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathUtil {
    private static String getAbsoluteProjectPath() {
        return PathUtil.class.getResource("/").getPath().replaceAll("^(/)|(target/ParsePowerPointWithApachePOI/WEB-INF/classes/)$", "");
    }

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/
     */
    public static String getAbsoluteLocalResourcePath() {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/NO1PPTS/${no1pptId}/
     */
    public static String getAbsolutePptPath(String no1pptId) {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/NO1PPTS/" + no1pptId + "/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/PPT2IMG/${no1pptId}/
     */
    public static String getAbsolutePpt2imgPath(String no1pptId) {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/PPT2IMG/" + no1pptId + "/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ZIPEDPPT/
     */
    public static String getAbsoluteZipedPptPath(String no1pptId) {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/ZIPEDPPT/" + no1pptId + "/";
    }

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/BAIDUIMGS/
     */
    public static String getAbsoluteBaiduImgPath() {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/BAIDUIMGS/";
    }

    /**
     * 根据 pptId获取到对应目录下的第一个 ppt/pptx 文件，如果文件不存在则返回Null
     *
     * @param pptId pptId
     * @return ppt的File对象
     */
    public static File getPptFile(String pptId) {
        File[] files = new File(getAbsolutePptPath(pptId)).listFiles();
        List<File> fileList = getPptFile(files, new ArrayList<>());
        for (File file : fileList) {
            String fileName = file.getName().toLowerCase();
            if (fileName.contains(".pptx") || fileName.contains(".ppt")) {
                return file;
            }
        }
        return null;
    }

    /**
     * 递归得到 files 路径下的第一个PPT/PPTX文件
     *
     * @param files new File(getAbsolutePptPath(pptId)).listFiles()
     * @return 得到的第一个PPT/PPTX文件File对象
     */
    private static List<File> getPptFile(File[] files, List<File> fileList) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getPptFile(file.listFiles(), fileList);
                } else if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.toLowerCase().contains(".ppt") || fileName.toLowerCase().contains(".pptx")) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }
}
