package com.parse.ppt.poi.service.common.cache;

import com.parse.ppt.poi.entity.User;
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
     * @param keyPairMap 需要存入的map信息
     * @return 返回码
     */
    String addToRedisCache(Map<String, String> keyPairMap);

    /**
     * 从Redis中根据 key读取相应的value
     *
     * @param key key
     * @return value
     */
    String getByKey(String key);

    /**
     * 从Redis移除一组键值对信息
     *
     * @param key key
     */
    void removeFromRedis(String key);

    /**
     * 根据用户对象的 username属性 增加/修改用户对象专属的持久化的公钥-密钥对，用户后期的相关操作可以使用该公钥-密钥对进行加密、解密
     *
     * @param username  用户名
     * @param oldPrefix 先前的 Redis key 的前缀
     * @return 返回码
     */
    String addUserPersistentKeyPair(String username, String oldPrefix);

}
