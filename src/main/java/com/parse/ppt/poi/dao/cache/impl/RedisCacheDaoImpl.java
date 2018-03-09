package com.parse.ppt.poi.dao.cache.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.cache.RedisCacheDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author Jupiter
 * @date 2018/03/01/11:33
 */
@Repository
public class RedisCacheDaoImpl implements RedisCacheDao {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisCacheDaoImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String add(String key, String value) {
        logger.info("------->  start ! " +
                "  key = " + key +
                "  value = " + value);
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
    public String get(String key) {
        try {
            logger.info("------->  start ! " +
                    "  key = " + key);
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            String result = operator.get(key);
            logger.info("------->  end!  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String remove(String key) {
        logger.info("------->  start ! " +
                "  key = " + key);
        try {
            boolean result = redisTemplate.delete(key);
            logger.info("------->  end !   result = " + result);
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
}
