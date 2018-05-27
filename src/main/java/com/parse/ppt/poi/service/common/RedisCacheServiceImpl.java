package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.RedisCacheDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/03/01/15:46
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RedisCacheDao redisCacheDao;

    @Autowired
    public RedisCacheServiceImpl(RedisCacheDao redisCacheDao) {
        this.redisCacheDao = redisCacheDao;
    }

    @Override
    public String addToRedisCache(String key, String value) {
        logger.info("------->  start! " +
                "  key = " + key +
                "  value = " + value);
        try {
            String result = redisCacheDao.add(key, value);
            logger.info("------->  end! " +
                    "  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String addToRedisCache(Map<String, String> keyPairMap) {
        logger.info("------->  start! " +
                "  map = " + keyPairMap);
        try {
            String result = ReturnCode.SUCCESS;
            for (String key : keyPairMap.keySet()) {
                String value = keyPairMap.get(key);
                String tempResult = redisCacheDao.add(key, value);
                if (tempResult.equals(ReturnCode.FAILED)) {
                    logger.warn("------->  FAILED!  " +
                            "   key = " + key +
                            "   value = " + value);
                    result = ReturnCode.FAILED;
                }
            }
            // map 中 每一项都成功添加到Redis中
            if (result.equals(ReturnCode.SUCCESS)) {
                logger.info("------->  end !   SUCCESS");
                return ReturnCode.SUCCESS;
            } else {
                // 有的项添加失败
                logger.info("------->  end !  FAILED ");
                return ReturnCode.FAILED;
            }
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String getByKey(String key) {
        logger.info("------->  start! " +
                "  key = " + key);
        try {
            String value = redisCacheDao.get(key);
            logger.info("------->  end! " +
                    "  value = " + value);
            return value;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public void removeFromRedis(String key) {
        logger.info("------->  start! " +
                "  key = " + key);
        try {
            String result = redisCacheDao.remove(key);
            logger.info("------->  end! " +
                    "  result = " + result);
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
    }

    @Override
    public String addUserPersistentKeyPair(String username, String oldPrefix) {
        logger.info("------->  start! " +
                "  username = " + username +
                "  oldPrefix = " + oldPrefix);
        try {
            // 从 Redis缓存中 中获取到 公钥、密钥
            String publicKey = getByKey(oldPrefix.trim() + ".publicKey");
            String privateKey = getByKey(oldPrefix.trim() + ".privateKey");
            // 获取到了之前的公钥、密钥后，将其从Redis缓存中移除
            removeFromRedis(oldPrefix.trim() + ".publicKey");
            removeFromRedis(oldPrefix.trim() + ".privateKey");
            // 添加持久化的公钥-密钥 对到Redis缓存中
            String resultA = addToRedisCache(username.trim() + ".publicKey", publicKey);
            String resultB = addToRedisCache(username.trim() + ".privateKey", privateKey);

            logger.info("------->  end! " +
                    "  resultA = " + resultA +
                    "  resultB = " + resultB);
            return (resultA.equals(resultB) && resultA.equals(ReturnCode.SUCCESS)) ? ReturnCode.SUCCESS : ReturnCode.FAILED;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}