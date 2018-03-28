package com.parse.ppt.poi.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class PathUtil {
    /**
     * 公用方法，获取到当前项目的根目录
     *
     * @return ${AbsoluteProjectPath}/
     */
    public static String getAbsoluteProjectPath() {
        String targetRegex = "^(/)|((target/ParsePowerPointWithApachePOI/WEB-INF/classes/)|(target/test-classes/))";
        return PathUtil.class.getResource("/").getPath().replaceAll(targetRegex, "");
    }

    /**
     * 公用方法，获取到当前项目的本地资源根目录
     *
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/
     */
    public static String getAbsoluteLocalResourcesPath() {
        return getAbsoluteProjectPath() + "/ZeroFilesOutput/";
    }

    /**
     * 公用方法，获取到当前项目的本地PPT资源目录
     *
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/
     */
    public static String getAbsolutePptPath() {
        return getAbsoluteLocalResourcesPath() + "ppts/";
    }

    /**
     * 公用方法，获取到当前项目的本地PPT转图片后的图片目录
     *
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/
     */
    public static String getAbsolutePpt2ImgPath() {
        return getAbsoluteLocalResourcesPath() + "ppt2imgs/";
    }

    /* 百度图片路径 */

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/baiduImgs/
     */
    public static String getAbsoluteBaiduImgPath() {
        return getAbsoluteLocalResourcesPath() + "baiduImgs/";
    }

    /* 获取No1PPT处理路径的相关方法 */

    /**
     * @param no1pptId no1pptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/no1ppts/${no1pptId}/
     */
    public static String getAbsoluteNo1PptPath(String no1pptId) {
        return getAbsolutePptPath() + "no1ppts/" + no1pptId + "/";
    }

    /**
     * @param no1pptId no1pptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/no1ppt2imgs/${no1pptId}/
     */
    public static String getAbsoluteNo1PPT2imgPath(String no1pptId) {
        return getAbsolutePpt2ImgPath() + "no1ppt2imgs/" + no1pptId + "/";
    }

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/zipedNo1ppts/
     */
    public static String getAbsoluteZipedNo1PptPath() {
        return getAbsolutePptPath() + "zipedNo1ppts/";
    }

    /**
     * 根据 pptId获取到对应目录下的第一个 ppt/pptx 文件，如果文件不存在则返回Null
     *
     * @param no1pptId no1pptId
     * @return ppt的File对象
     */
    public static File getNo1PptFile(String no1pptId) {
        File[] files = new File(getAbsoluteNo1PptPath(no1pptId)).listFiles();
        List<File> fileList = new ArrayList<>();
        getPptFileList(files, fileList);
        if (fileList.size() > 0) {
            return fileList.get(0);
        }
        return null;
    }

    /* 获取PoiPPT-Rebuild处理路径的相关方法 */

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Rebuild/
     */
    public static String getAbsolutePoiRebuildPptPath() {
        return getAbsolutePptPath() + "PoiPPTS/Rebuild/";
    }

    /**
     * @param poiPptId poiPptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Rebuild/${poiPptId}/
     */
    public static String getAbsolutePoiRebuildPptPath(String poiPptId) {
        return getAbsolutePptPath() + "PoiPPTS/Rebuild/" + poiPptId + "/";
    }

    /**
     * @param poiPptId poiPptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/PoiPPTS/Rebuild/${poiPptId}/
     */
    public static String getAbsolutePoiRebuildPPT2imgPath(String poiPptId) {
        return getAbsolutePpt2ImgPath() + "/PoiPPTS/Rebuild/" + poiPptId + "/";
    }

    /* 获取PoiPPT-Generate处理路径的相关方法 */

    /**
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Generate/
     */
    public static String getAbsolutePoiGeneratePptPath() {
        return getAbsolutePptPath() + "PoiPPTS/Generate/";
    }

    /**
     * @param poiPptId poiPptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Generate/${poiPptId}/
     */
    public static String getAbsolutePoiGeneratePptPath(String poiPptId) {
        return getAbsolutePptPath() + "PoiPPTS/Generate/" + poiPptId + "/";
    }

    /**
     * @param poiPptId poiPptId
     * @return ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/PoiPPTS/Generate/${poiPptId}/
     */
    public static String getAbsolutePoiGeneratePPT2imgPath(String poiPptId) {
        return getAbsolutePpt2ImgPath() + "/PoiPPTS/Generate/" + poiPptId + "/";
    }

    /**
     * 根据 pptId,pptTag 获取到 pptId 对应的 File 对象
     *
     * @param pptId  pptID
     * @param pptTag TYPE_NO1 - "NO1"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_REBUILD - "POI_REBUILD"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_GENERATE - "POI_GENERATE"
     * @return TYPE_NO1 - ${AbsoluteProjectPath}/ZeroFilesOutput/NO1PPTS/${pptID}/<br>
     * TYPE_POI_REBUILD - ${AbsoluteProjectPath}/ZeroFilesOutput/POIPPTS/REBUILD/${pptID}/<br>
     * TYPE_POI_GENERATE - ${AbsoluteProjectPath}/ZeroFilesOutput/POIPPTS/GENERATE/${pptID}/
     */
    public static File getPptFileByTag(String pptId, String pptTag) {
        File[] files = null;
        switch (pptTag) {
            case PptTag.TYPE_NO1:
                files = new File(getAbsoluteNo1PptPath(pptId)).listFiles();
                break;
            case PptTag.TYPE_POI_REBUILD:
                files = new File(getAbsolutePoiRebuildPptPath(pptId)).listFiles();
                break;
            case PptTag.TYPE_POI_GENERATE:
                files = new File(getAbsolutePoiGeneratePptPath(pptId)).listFiles();
                break;
            default:
                return null;
        }
        List<File> fileList = new ArrayList<>();
        getPptFileList(files, fileList);
        if (fileList.size() > 0) {
            return fileList.get(0);
        }
        return null;
    }


    /* 通用方法 */

    /**
     * 根据 pptId,pptTag 获取到PPT的路径，目前用于 Ppt2ImgService.ppt2img() 方法中
     *
     * @param pptId  pptID
     * @param pptTag TYPE_NO1 - "NO1"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_REBUILD - "POI_REBUILD"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_GENERATE - "POI_GENERATE"
     * @return TYPE_NO1 - ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/no1ppts/${pptID}/<br>
     * TYPE_POI_REBUILD - ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Rebuild/${pptID}/<br>
     * TYPE_POI_GENERATE - ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Generate/${pptID}/<br>
     * others - null
     */
    public static String getAbsolutePptPathByTag(String pptId, String pptTag) {
        String targetPath = null;
        switch (pptTag) {
            case PptTag.TYPE_NO1:
                targetPath = getAbsoluteNo1PptPath(pptId);
                break;
            case PptTag.TYPE_POI_REBUILD:
                targetPath = getAbsolutePoiRebuildPptPath(pptId);
                break;
            case PptTag.TYPE_POI_GENERATE:
                targetPath = getAbsolutePoiGeneratePptPath(pptId);
                break;
            default:
                targetPath = null;
                break;
        }
        return targetPath;
    }

    /**
     * 根据 pptId,pptTag 获取到PPT的路径，目前用于 Ppt2ImgService.ppt2img() 方法中
     *
     * @param pptTag TYPE_POI_REBUILD - "POI_REBUILD"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_GENERATE - "POI_GENERATE"
     * @return TYPE_POI_REBUILD - ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Rebuild/${this.listFiles().length+1}/<br>
     * TYPE_POI_GENERATE - ${AbsoluteProjectPath}/ZeroFilesOutput/ppts/PoiPPTS/Generate/${this.listFiles().length+1}/<br>
     * others - null
     */
    public static String getAbsolutePoiPptPathByTag(String pptTag) {
        String targetPath = null;
        switch (pptTag) {
            case PptTag.TYPE_POI_REBUILD:
                targetPath = getAbsolutePoiRebuildPptPath();
                break;
            case PptTag.TYPE_POI_GENERATE:
                targetPath = getAbsolutePoiGeneratePptPath();
                break;
            default:
                targetPath = null;
                break;
        }
        if (targetPath != null) {
            File file = new File(targetPath);
            if (!file.exists()) {
                boolean isMkdirs = file.mkdirs();
            }
            int folderNumIndex = 1;
            File[] folders = file.listFiles();
            if (folders != null) {
                folderNumIndex = folders.length + 1;
            }
            targetPath = targetPath + folderNumIndex + "/";
            file = new File(targetPath);
            if (!file.exists()) {
                boolean isMkdirs = file.mkdirs();
            }
        }
        return targetPath;
    }

    /**
     * 根据 pptId,pptTag 获取到PPT转换为图片后图片的存储路径，目前用于 Ppt2ImgService.ppt2img() 方法中
     *
     * @param pptId  pptID
     * @param pptTag TYPE_NO1 - "NO1"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_REBUILD - "POI_REBUILD"<br>
     *               &emsp;&emsp;&emsp;&emsp;TYPE_POI_GENERATE - "POI_GENERATE"
     * @return TYPE_NO1 - ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/no1ppt2imgs/${pptID}/<br>
     * TYPE_POI_REBUILD - ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/PoiPPTS/Rebuild/${pptID}/<br>
     * TYPE_POI_GENERATE - ${AbsoluteProjectPath}/ZeroFilesOutput/ppt2imgs/PoiPPTS/Generate/${pptID}/<br>
     * other - null
     */
    public static String getAbsolutePpt2ImgPathByTag(String pptId, String pptTag) {
        String targetPath = null;
        switch (pptTag) {
            case PptTag.TYPE_NO1:
                targetPath = getAbsoluteNo1PPT2imgPath(pptId);
                break;
            case PptTag.TYPE_POI_REBUILD:
                targetPath = getAbsolutePoiRebuildPPT2imgPath(pptId);
                break;
            case PptTag.TYPE_POI_GENERATE:
                targetPath = getAbsolutePoiGeneratePPT2imgPath(pptId);
                break;
            default:
                targetPath = null;
                break;
        }
        return targetPath;
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

}
