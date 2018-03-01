package com.parse.ppt.poi.service.common.encrypt;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * RSA 加密、解密Service
 *
 * @author Jupiter
 * @date 2018/02/28/11:28
 */
@Service
public interface EncryptService {

    /**
     * 获取 公钥、密钥 对, map对应的 key 为  publicKey , privateKey
     *
     * @return key pair Map
     */
    Map<String, String> getKeyPair();

    /**
     * 获取 公钥、密钥 对，map对应的 key 为  prefix.publicKey , prefix.privateKey
     *
     * @param prefix 返回map的key的前缀
     * @return key pair
     */
    Map<String, String> getKeyPair(String prefix);

    /**
     * 使用密钥对已加密的内容进行解密
     *
     * @param privateKey 私钥
     * @param content    已加密的内容
     * @return 解密后的内容
     */
    String contentDecrypter(String privateKey, String content);

    /**
     * 使用公钥对内容进行加密
     *
     * @param publicKey 公钥
     * @param content   已加密的内容
     * @return 解密后的内容
     */
    String contentEncrypter(String publicKey, String content);
}
