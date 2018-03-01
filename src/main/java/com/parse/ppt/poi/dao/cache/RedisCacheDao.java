package com.parse.ppt.poi.dao.cache;

import org.springframework.stereotype.Repository;

/**
 * @author Jupiter
 * @date 2018/03/01/11:21
 */
@Repository
public interface RedisCacheDao {
    /**
     * 向Redis高速缓存中新增一个键值对
     *
     * @param key   key
     * @param value value
     * @return 返回码
     */
    String add(String key, String value);

    /**
     * 读取Redis高速缓存中的键值对
     *
     * @param key key
     * @return 读取结果 Value
     */
    String get(String key);
}
