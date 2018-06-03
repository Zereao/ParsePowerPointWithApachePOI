package com.parse.ppt.poi.dao;

import com.parse.ppt.poi.common.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Redis DAO
 *
 * @author Jupiter
 * @version 2018/03/01/11:33
 */
@Repository
public class RedisCacheDaoImpl implements RedisCacheDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisCacheDaoImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String add(String key, String value) {
        logger.info("------->  start !   key = {}   value = {}", key, value);
        try {
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            operator.set(key, value);
            logger.info("------->  end ! ");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String add(Map<String, String> paramsMap) {
        logger.info("------->  start !   paramsMap = {}=", paramsMap);
        try {
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                operator.set(key, value);
            }
            logger.info("------->  end ! ");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String get(String key) {
        try {
            logger.info("------->  start !   key = {}", key);
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            String result = operator.get(key);
            logger.info("------->  end!  result = {}", result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String remove(String key) {
        logger.info("------->  start !   key = {}", key);
        try {
            boolean result = redisTemplate.delete(key);
            logger.info("------->  end !   result = {}", result);
            if (result) {
                return ReturnCode.SUCCESS;
            } else {
                return ReturnCode.KEY_NOT_EXISTS;
            }
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String addUserPersistentKeyPair(String oldPrefix, Map<String, String> newKeyPairMap) {
        logger.info("------->  start!   oldPrefix = {}    newKeyPairMap = {}", oldPrefix, newKeyPairMap);
        try {
            // 获取到了之前的公钥、密钥后，将其从Redis缓存中移除
            remove(oldPrefix.trim() + ".publicKey");
            remove(oldPrefix.trim() + ".privateKey");
            for (Map.Entry<String, String> entry : newKeyPairMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String result = add(key, value);
                logger.info("------->   key = {}   value = {}    result = {}", key, value, result);
            }
            logger.info("------->  end!");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
