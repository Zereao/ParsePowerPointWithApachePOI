package com.parse.ppt.poi.service.encrypt.impl;

import com.parse.ppt.poi.service.encrypt.EncryptService;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/02/28/11:28
 */
@Service
public class EncryptServiceImpl implements EncryptService {
    private Logger logger = LogManager.getLogger(this.getClass());
    /**
     * 加密算法RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * the keysize. 特定算法的度量标准
     */
    private static final int KEY_SIZE = 512;

    private String publicKey;
    private String privateKey;

    @Override
    public String getPublicKey() {
        keyPairGenerator();
        logger.info("EncryptServiceImpl.getPublicKey   ------->  publicKey = " + publicKey);
        return publicKey;
    }

    @Override
    public String getPrivateKey() {
        keyPairGenerator();
        logger.info("EncryptServiceImpl.getPrivateKey   ------->  privateKey = " + privateKey);
        return privateKey;
    }

    @Override
    public Map<String, String> getKeyPair() {
        keyPairGenerator();
        Map<String, String> map = new HashMap<>(2);
        map.put("publicKey", publicKey);
        map.put("privateKey", privateKey);
        logger.info("EncryptServiceImpl.getKeyPair   ------->  keyPairMap " + map);
        return map;
    }

    @Override
    public String contentDecrypter(String publicKey, String privateKey, String content) {
        logger.info("EncryptServiceImpl.contentDecrypter   ------->  start ! \n" +
                "  publicKey = " + publicKey + "\n" +
                "  privateKey = " + privateKey + "\n" +
                "  content = " + content);
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
            logger.info("EncryptServiceImpl.contentDecrypter   ------->  end !  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("EncryptServiceImpl.contentDecrypter   ------->  ERROR ! 返回 null !");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 用于生成公钥、私钥 对的私密方法。在上面每个方法内调用，是为了每次获取到的公钥私钥都不一样
     */
    private void keyPairGenerator() {
        logger.info("EncryptServiceImpl.keyPairGenerator   ------->  start! ");
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //公钥
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            //私钥
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
            privateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
            logger.info("EncryptServiceImpl.keyPairGenerator   ------->  end! \n" +
                    "   publicKey = " + publicKey + "\n" +
                    "   privateKey = " + privateKey);
        } catch (Exception e) {
            logger.error("EncryptServiceImpl.keyPairGenerator   ------->  ERROR ! " + e.getMessage());
        }
    }
}