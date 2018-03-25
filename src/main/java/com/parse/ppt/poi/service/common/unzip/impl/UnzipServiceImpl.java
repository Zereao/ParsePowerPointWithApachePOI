package com.parse.ppt.poi.service.common.unzip.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.service.common.unzip.UnzipService;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;

@Deprecated
@Service
public class UnzipServiceImpl implements UnzipService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String getPptName(String zipFileName) {
        logger.info("------->  start!  zipFileName = " + zipFileName);
        String zipFilePath = "文件输出/NO1PPTS/" + zipFileName;
        try (
                InputStream inputStream = new FileInputStream(new File(zipFilePath));
                ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "GBK", false, true);
        ) {
            ArchiveEntry archiveEntry = null;
            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //获取文件名
                String archiveEntryFileName = archiveEntry.getName();
                boolean isPPTFile = archiveEntryFileName.toLowerCase().contains(".ppt") ||
                        archiveEntryFileName.toLowerCase().contains(".pptx");
                if (isPPTFile) {
                    logger.info("------->  end!  PPT文件的文件名为 = " + archiveEntryFileName);
                    return archiveEntryFileName;
                }
            }
            logger.info("------->  end!  压缩包中不存在PPT/PPTX文件！    返回 null ");
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String unzipFileByName(String zipFileName) {
        logger.info("------->  start!  zipFileName = " + zipFileName);

        final String BASE_PATH = "文件输出/NO1PPTS/";
        String zipFilePath = BASE_PATH + zipFileName;
//        String zipFilePath = BASE_PATH + "PPTS/" + zipFileName;
        final String UNZIP_BATH_PATH = BASE_PATH + "unzip/" + zipFileName.substring(0, zipFileName.length() - 4) + "/";
        OutputStream outputStream = null;
        // try-with-resource
        try (
                InputStream inputStream = new FileInputStream(new File(zipFilePath));
                ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "GBK", false, true);
        ) {
            ArchiveEntry archiveEntry = null;
            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //获取文件名
                String archiveEntryFileName = archiveEntry.getName();
                //构造解压后文件的存放路径 - 去除扩展名
                String archiveEntryPath = UNZIP_BATH_PATH + archiveEntryFileName;
                byte[] content = new byte[(int) archiveEntry.getSize()];
                int readResult = zipArchiveInputStream.read(content);
                //把解压出来的文件写到指定路径
                File entryFile = new File(archiveEntryPath);
                if (!entryFile.exists()) {
                    boolean isMkdirs = entryFile.getParentFile().mkdirs();
                }
                outputStream = new FileOutputStream(entryFile);
                outputStream.write(content);
                outputStream.flush();
            }
            logger.info("------->  result = " + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  result = " + ReturnCode.FAILED);
            logger.error(e.getMessage());
        } finally {
            try {
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                logger.error("outputStream.close() ERROR !    outputStream 关闭失败！");
                logger.error(e.getMessage());
            }
        }
        return ReturnCode.FAILED;
    }
}
