package com.parse.ppt.poi.service.common;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/02/28/11:28
 */
@Service
public class EncryptServiceImpl implements EncryptService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 加密算法RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * the keysize. 特定算法的度量标准
     */
    private static final int KEY_SIZE = 512;

    @Override
    public Map<String, String> getKeyPair() {
        logger.info("------->  start! ");
        try {
            Map<String, String> resultMap = keyPairGenerator("");
            logger.info("------->  end!    resultMap = {}", resultMap);
            return resultMap;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, String> getKeyPair(String prefix) {
        logger.info("------->  start!   prefix = {}", prefix.trim());
        try {
            Map<String, String> resultMap = keyPairGenerator(prefix.trim());
            logger.info("------->  end!  resultMap = {}", resultMap);
            return resultMap;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String contentDecrypter(String privateKey, String content) {
        logger.info("------->  start ! \nprivateKey = {}\ncontent = {}", privateKey, content);
        try {
            byte[] encodeKey = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey rsaPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            byte[] contentByte = Base64.decodeBase64(content);
            byte[] resultByte = cipher.doFinal(contentByte);
            String result = new String(resultByte);
            logger.info("------->  end !  result = {}", result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR ! 返回 null !");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String contentEncrypter(String publicKey, String content) {
        logger.info("------->  start !\npublicKey = {}\ncontent = {}", publicKey, content);
        try {
            byte[] encodeKey = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey rsaPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            byte[] contentByte = Base64.decodeBase64(content);
            byte[] resultByte = cipher.doFinal(contentByte);
            String result = Base64.encodeBase64String(resultByte);
            logger.info("------->  end !  result = {}", result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR ! 返回 null !");
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 私密的 公钥-密钥 对生成方法，被上面的一些方法调用时，每次产生的公钥、密钥都不相同
     *
     * @param prefix 公钥、密钥 map 的key的前缀，传入 null 或 "" 的时候，默认 key 分别为 publicKey，privateKey
     * @return keyPairMap
     */
    private Map<String, String> keyPairGenerator(String prefix) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   prefix = {}", prefix);
        }
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //公钥
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            //私钥
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            String publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
            String privateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
            Map<String, String> resultMap = new HashMap<>(2);
            String theKeyOfPublicKey = "publicKey";
            String theKeyOfPrivate = "privateKey";
            // 前缀不为null并且有内容，不为 ""
            boolean prefixIsNotNull = prefix != null && (!("".equals(prefix)));
            if (prefixIsNotNull) {
                theKeyOfPublicKey = prefix + ".publicKey";
                theKeyOfPrivate = prefix + ".privateKey";
            }
            resultMap.put(theKeyOfPublicKey, publicKey);
            resultMap.put(theKeyOfPrivate, privateKey);
            if (logger.isDebugEnabled()) {
                logger.info("------->  end!\npublicKey = {}\nprivateKey = {}", publicKey, privateKey);
            }
            return resultMap;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return null;
    }
}