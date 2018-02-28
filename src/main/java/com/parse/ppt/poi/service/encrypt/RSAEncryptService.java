package com.parse.ppt.poi.service.encrypt;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/02/28/11:28
 */
@Service
public interface RSAEncryptService {
    /**
     * 一个新的用户登录请求，获取公钥
     *
     * @return publicKey
     */
    String getPublicKey();

    /**
     * 一个新的用户登录请求，获取秘钥
     *
     * @return privateKey
     */
    String getPrivateKey();

    /**
     * 一个新的用户登录请求，获取 公钥、密钥 对
     *
     * @return privateKey
     */
    Map<String, String> getKeyPair();

    /**
     * 使用公钥、密钥对已加密的内容进行解密
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @param content    已加密的内容
     * @return 解密后的内容
     */
    String contentDecrypter(String publicKey, String privateKey, String content);
}
