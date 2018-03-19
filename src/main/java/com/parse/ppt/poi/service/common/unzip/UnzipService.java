package com.parse.ppt.poi.service.common.unzip;

import org.springframework.stereotype.Service;

@Service
public interface UnzipService {
    /**
     * 根据文件名解压.zip格式的压缩文件
     *
     * @param zipFileName .zip压缩文件的文件名
     * @return ReturnCode-返回码
     */
    String unzipFileByName(String zipFileName);
}
