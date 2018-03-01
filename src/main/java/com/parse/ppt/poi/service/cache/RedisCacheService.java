package com.parse.ppt.poi.service.cache;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/02/28/19:24
 */
@Service
public interface RedisCacheService {
    /**
     * 向Redis中写入一组键值对信息
     *
     * @param key   key
     * @param value value
     * @return 返回码
     */
    String addToRedisCache(String key, String value);

    /**
     * 向Redis中写入多组键值对信息，信息存于 map 中，要求键值对的键和值都是String类型的
     *
     * @param map 需要存入的map信息
     * @return 返回码
     */
    String addToRedisCache(Map<String, String> map);

    /**
     * 从Redis中根据key读取相应的value
     *
     * @param key key
     * @return value
     */
    String getByKey(String key);

    /**
     * 从Redis移除一组键值对信息
     *
     * @param key key
     * @return 返回码
     */
    String removeFromRedis(String key);
}
