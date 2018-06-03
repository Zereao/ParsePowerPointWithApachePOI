package com.parse.ppt.poi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己定义的 路径工具类，通过出入的参数，得到对应的绝对路径
 *
 * @author Jupiter
 * @version 2018/03/07 23:30
 */
@SuppressWarnings("WeakerAccess")
public class PathUtil {
    /*
     * 目录结构：
     *
     * FilesHub-
     *         -PPT-
     *             -No1PPT
     *             -PoiPPT
     *         -Img-
     *             -No1PPT2Img
     *             -PoiPPT2Img
     *             -BaiDuImg
     *         -ZipedPPT
     *         -Other
     * */

    private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);

    /**
     * 公用方法，如果路径对应的一个或多个文件夹不存在，则创建它们
     *
     * @param path 目标路径
     */
    public static void mkDirsIfNotExists(String path) {
        File file = new File(path);
        boolean isExists = file.exists();
        boolean isMkDirs = false;
        if (!isExists) {
            isMkDirs = file.mkdirs();
        }
        if (logger.isDebugEnabled()) {
            logger.info("------->   file.exists() = {}，  file.mkdirs() = {}", isExists, isMkDirs);
        }
    }

    /**
     * 公用方法，获取到当前项目的根目录
     *
     * @return ${ProjectPath}/
     */
    public static String getProjectPath() {
        String targetRegex = "^(/)|((target/ParsePowerPointWithApachePOI/WEB-INF/classes/)|(target/test-classes/))";
        return PathUtil.class.getResource("/").getPath().replaceAll(targetRegex, "");
    }

    /**
     * 公用方法，获取到当前项目的本地资源仓库根目录
     *
     * @return ${ProjectPath}/FilesHub/
     */
    public static String getFilesHubPath() {
        return getProjectPath() + "/FilesHub/";
    }

    /**
     * 公用方法，获取到当前项目的本地PPT资源目录
     *
     * @return ${ProjectPath}/FilesHub/PPT/
     */
    public static String getPptPath() {
        return getFilesHubPath() + "PPT/";
    }

    /**
     * 公用方法，获取到当前项目的本地PPT转图片后的图片目录
     *
     * @return ${ProjectPath}/FilesHub/Img/
     */
    public static String getImgPath() {
        return getFilesHubPath() + "Img/";
    }

    /**
     * @return ${ProjectPath}/FilesHub/PPT/No1PPT/
     */
    public static String getNo1PptBasePath() {
        return getPptPath() + "No1PPT/";
    }

    /**
     * @return ${ProjectPath}/FilesHub/PPT/PoiPPT/
     */
    public static String getPoiPptBasePath() {
        return getPptPath() + "PoiPPT/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${ProjectPath}/FilesHub/PPT/No1PPT/${no1pptId}/
     */
    public static String getNo1PptPath(String no1pptId) {
        String targetPath = getNo1PptBasePath() + no1pptId + "/";
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetPath = {}", targetPath);
        }
        mkDirsIfNotExists(targetPath);
        return targetPath;
    }

    /**
     * @param no1pptId PoiPPT对象对应的no1pptId
     * @return ${ProjectPath}/FilesHub/PPT/PoiPPT/${no1pptId}/
     */
    public static String getPoiPptPath(String no1pptId) {
        String targetPath = getPoiPptBasePath() + no1pptId + "/";
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetPath = {}", targetPath);
        }
        mkDirsIfNotExists(targetPath);
        return targetPath;
    }


    /**
     * @param no1pptId no1pptId
     * @return ${ProjectPath}/FilesHub/PPT2Img/No1PPT2Img/${no1pptId}/
     */
    public static String getNo1Ppt2ImgPath(String no1pptId) {
        String targetPath = getImgPath() + "No1PPT2Img/" + no1pptId + "/";
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetPath = {}", targetPath);
        }
        mkDirsIfNotExists(targetPath);
        return targetPath;
    }

    /**
     * 根据 no1pptId获取到对应目录下的第一个No1PPT对应的ppt/pptx 文件，如果文件不存在则返回Null
     *
     * @param no1pptId no1pptId
     * @return ppt的File对象<br>如果文件不存在，则返回null
     */
    public static File getNo1PptFile(String no1pptId) {
        File[] files = new File(getNo1PptPath(no1pptId)).listFiles();
        List<File> fileList = new ArrayList<>();
        getPptFileList(files, fileList);
        if (fileList.size() > 0) {
            File file = fileList.get(0);
            if (logger.isDebugEnabled()) {
                logger.info("------->   targetFile = {}", file.getAbsolutePath());
            }
            return file;
        }
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetFile = [null]");
        }
        return null;
    }

    /**
     * @param no1pptId PoiPPT对象对应的no1pptId
     * @return ${ProjectPath}/FilesHub/ppt2imgs/PoiPPT/${no1pptId}/
     */
    public static String getPoiPpt2imgPath(String no1pptId) {
        String targetPath = getImgPath() + "/PoiPPT/" + no1pptId + "/";
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetPath = {}", targetPath);
        }
        mkDirsIfNotExists(targetPath);
        return targetPath;
    }

    /**
     * 根据 no1pptId获取到对应目录下的第一个PoiPPT对应的ppt/pptx 文件，如果文件不存在则返回Null
     *
     * @param no1pptId PoiPPT对应的no1pptId
     * @return ppt的File对象<br>如果文件不存在，则返回null
     */
    public static File getPoiPptFile(String no1pptId) {
        File[] files = new File(getPoiPptPath(no1pptId)).listFiles();
        List<File> fileList = new ArrayList<>();
        getPptFileList(files, fileList);
        if (fileList.size() > 0) {
            File file = fileList.get(0);
            if (logger.isDebugEnabled()) {
                logger.info("------->   targetFile = {}", file.getAbsolutePath());
            }
            return file;
        }
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetFile = [null]");
        }
        return null;
    }

    /**
     * 递归删除 folder 目录以及其目录下所有的 文件、文件夹
     *
     * @param folder targetFolder
     */
    public static void deleteDir(File folder) {
        boolean result = false;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    result = file.delete();
                } else {
                    deleteDir(file);
                }
            }
        }
        result = folder.delete();
    }


    /**
     * 私有公用方法：递归得到 files 路径下的所有PPT/PPTX文件
     *
     * @param files      new File(pptPath).listFiles()
     * @param resultList 保存了结果的resultList
     */
    private static void getPptFileList(File[] files, List<File> resultList) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归
                    getPptFileList(file.listFiles(), resultList);
                } else if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.toLowerCase().contains(".ppt") || fileName.toLowerCase().contains(".pptx")) {
                        resultList.add(file);
                    }
                }
            }
        }
    }

//  下面的方法暂时备用

    /* PPT 压缩包存储路径 */

    /**
     * @return ${ProjectPath}/FilesHub/ZipedPPT/
     */
    public static String getZipedPPTPath() {
        String targetPath = getFilesHubPath() + "/ZipedPPT/";
        if (logger.isDebugEnabled()) {
            logger.info("------->   targetPath = {}", targetPath);
        }
        mkDirsIfNotExists(targetPath);
        return targetPath;
    }


    /* 百度图片路径 */

    /**
     * @return ${ProjectPath}/FilesHub/Img/BaiDuImg
     */
    public static String getBaiduImgPath() {
        return getImgPath() + "BaiDuImg/";
    }
}
