package com.parse.ppt.poi.service.cache.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.cache.RedisCacheDao;
import com.parse.ppt.poi.service.cache.RedisCacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/03/01/15:46
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private RedisCacheDao redisCacheDao;

    @Override
    public String addToRedisCache(String key, String value) {
        logger.info("RedisCacheServiceImpl.addToRedisCache   ------->  start! " +
                "  key = " + key +
                "  value = " + value);
        try {
            String result = redisCacheDao.add(key, value);
            logger.info("RedisCacheServiceImpl.addToRedisCache   ------->  end! " +
                    "  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("RedisCacheServiceImpl.addToRedisCache   ------->  ERROR! " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String addToRedisCache(Map<String, String> map) {
        logger.info("RedisCacheServiceImpl.addToRedisCache   ------->  start! " +
                "  map = " + map);
        try {
            String result = redisCacheDao.add(map);
            logger.info("RedisCacheServiceImpl.addToRedisCache   ------->  end! " +
                    "  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("RedisCacheServiceImpl.addToRedisCache   ------->  ERROR! " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String getByKey(String key) {
        logger.info("RedisCacheServiceImpl.getByKey   ------->  start! " +
                "  key = " + key);
        try {
            String value = redisCacheDao.get(key);
            logger.info("RedisCacheServiceImpl.getByKey   ------->  end! " +
                    "  value = " + value);
            return value;
        } catch (Exception e) {
            logger.error("RedisCacheServiceImpl.getByKey   ------->  ERROR! " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String removeFromRedis(String key) {
        logger.info("RedisCacheServiceImpl.removeFromRedis   ------->  start! " +
                "  key = " + key);
        try {
            String result = redisCacheDao.remove(key);
            logger.info("RedisCacheServiceImpl.removeFromRedis   ------->  end! " +
                    "  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("RedisCacheServiceImpl.removeFromRedis   ------->  ERROR! " + e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
