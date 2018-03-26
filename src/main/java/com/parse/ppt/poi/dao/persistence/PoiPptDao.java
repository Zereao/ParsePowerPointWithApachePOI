package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.PoiPPT;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/3/25/10:41
 */
@Repository
public interface PoiPptDao {
    /**
     * 分页查询。
     *
     * @param pageIndex 分页-位置偏移量[索引]-查询开始的索引，从第pageIndex开始
     * @param pageSize  分页-需要取得的行数-需要取得的数据的条数
     * @return PPT信息对象List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM poi_ppt"
            + "     WHERE 1=1 "
            + "	    LIMIT #{pageIndex, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER} "
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.PoiPptDao.BaseResultMap")
    List<PoiPPT> getPoiPPT(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * 根据pptID 获取到一个PoiPPT对象
     *
     * @param poiPptId PoiPPT的ID
     * @return PoiPPT对象
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM poi_ppt"
            + "     WHERE 1=1 "
            + "	    AND id = #{poiPptId, jdbcType=INTEGER}"
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.PoiPptDao.BaseResultMap")
    PoiPPT getPoiPPTById(@Param("poiPptId") int poiPptId);

    /**
     * 增加PoiPPT对象
     *
     * @param poiPPT 爬取到的 ppt 的信息对象
     */
    void addPoiPPT(PoiPPT poiPPT);

    /**
     * 更新PoiPPT的信息   +++ mapper 中未实现
     *
     * @param poiPPT PoiPPT
     */
    void updatePoiPPT(PoiPPT poiPPT);

    /**
     * 更新PoiPPT的信息——修改压缩包中的PPT文件的文件名
     *
     * @param poiPptId       PoiPPT的ID
     * @param poiPptFileName 压缩包中的PPT文件的文件名
     */
    void updatePoiPptFileName(@Param("poiPptId") int poiPptId,
                              @Param("poiPptFileName") String poiPptFileName);
}
