package com.parse.ppt.poi.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Jupiter
 * @version 2018/03/01/11:21
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
     * 向Redis中添加多个键值对，而不是添加一个Map类型的数据
     *
     * @param paramsMap 参数Map
     * @return 返回码
     */
    String add(Map<String, String> paramsMap);

    /**
     * 读取Redis高速缓存中的键值对
     *
     * @param key key
     * @return 读取结果 Value
     */
    String get(String key);

    /**
     * 移除某一个键值对数据
     *
     * @param key key
     * @return 返回码
     */
    String remove(String key);

    /**
     * 移除原来的获取KeyPair的时候存入的KeyPair，并增加可用于持久化的用户KeyPair
     *
     * @param oldPrefix     之前的前缀
     * @param newKeyPairMap 新的用户KeyPairMap
     * @return 返回码
     */
    String addUserPersistentKeyPair(String oldPrefix, Map<String, String> newKeyPairMap);
}
