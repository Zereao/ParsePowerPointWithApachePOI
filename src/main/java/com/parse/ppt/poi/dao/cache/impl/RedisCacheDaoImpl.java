package com.parse.ppt.poi.dao.cache.impl;

import com.parse.ppt.poi.commom.ReturnCode;
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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String add(String key, String value) {
        logger.info("RedisCacheDaoImpl.add   ------->  start ! " +
                "  key = " + key +
                "  value = " + value);
        try {
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            operator.set(key, value);
            logger.info("RedisCacheDaoImpl.add   ------->  end ! ");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("RedisCacheDaoImpl.add   ------->  ERROR!  " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String get(String key) {
        try {
            logger.info("RedisCacheDaoImpl.get   ------->  start ! " +
                    "  key = " + key);
            ValueOperations<String, String> operator = redisTemplate.opsForValue();
            String result = operator.get(key);
            logger.info("RedisCacheDaoImpl.get   ------->  end!  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("RedisCacheDaoImpl.get   ------->  ERROR!  " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
