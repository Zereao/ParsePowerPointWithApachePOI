package com.parse.ppt.poi.service.common.unzip;

import org.springframework.stereotype.Service;

@Deprecated
@Service
public interface UnzipService {
    /**
     * 获取压缩文件中的PPT文件的文件名
     *
     * @param zipFileName .zip压缩文件的文件名
     * @return 压缩文件中的PPT文件的文件名
     */
    String getPptName(String zipFileName);

    /**
     * 根据文件名解压.zip格式的压缩文件 - [有BUG，未解决]
     *
     * @param zipFileName .zip压缩文件的文件名
     * @return ReturnCode-返回码
     */
    String unzipFileByName(String zipFileName);

}
