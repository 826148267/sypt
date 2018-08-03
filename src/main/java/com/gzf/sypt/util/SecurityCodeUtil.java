package com.gzf.sypt.util;

import com.gzf.sypt.exception.EnciphermentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author guozifan
 * @Description: 产生随机数
 * @date 2018/6/4 21:53
 */
public class SecurityCodeUtil {
    private static Logger logger = LoggerFactory.getLogger(SecurityCodeUtil.class);
    /**
     * 采用获取当前JVM内存的剩余量
     * 并作为种子进行Random（主要是产生六位数）
     * 将六位数置为key（盐值）
     * 对手机号号码进行MD5散列
     * 将key(盐值)作为验证码，放入map
     * 将密文放入map，用于验证
     * @return  map<key,value;ciphertext,value>
     */
    public static Map<String, String> getVerific(String param){
        logger.info("=======start=======");
        //明文 = 参数 + 验证码明文
        String plainText = null;
        //加密方法
        String encipherType = "MD5";
        //密钥(验证码)
        String verific = null;
        //密文(存进数据库，后期比对)
        String cipherText = null;
        long startTime = System.currentTimeMillis();
        //返回JVM空闲内存数
        long freeMemory = Runtime.getRuntime().freeMemory() * 100000;
        //产生随机数产生器，将freeMemory设置为种子
        Random random = new Random(freeMemory);
        //产生六位数key（盐值）
        int temp = (int) (random.nextDouble() * 1000000);
        //如果前面是0的话位数不足就重新来一次
        if (temp <= 99999){
            temp = (int) (random.nextDouble() * 1000000);
        }
        verific = String.valueOf(temp);
        //使用key作为盐值进行散列
        plainText = param + verific;
        cipherText = getCipherText(plainText, encipherType);
        //将密钥和密文存进
        Map<String, String> result = new HashMap<>();
        result.put("key", verific);
        result.put("cipher", cipherText);
        logger.info(String.valueOf(System.currentTimeMillis() - startTime));
        logger.info("============end=============");
        return result;
    }

    /**
     * 传入明文，并将密文转换成16进制字符串
     * @param plainText 明文
     * @param encipherType  加密类型
     * @return 密文
     */
    public static String getCipherText(String plainText, String encipherType){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(encipherType);
        } catch (NoSuchAlgorithmException e) {
            throw new EnciphermentException("加密工具类错误,错误加密类型：" + encipherType);
        }
        byte[] bytes = new byte[0];
        try {
            bytes = md5.digest(plainText.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持此编码方式");
        }
        return bytesToHex(bytes);
    }

    /**
     * 将二进制数组转化为16进制字符串
     * @param bytes 需要转化的二进制数组
     * @return  16进制字符串
     */
    public static String bytesToHex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer(bytes.length * 2);
        for (byte b : bytes){
            stringBuffer.append(String.format("%02x",new Integer(b & 0xff)));
        }
        return stringBuffer.toString();
    }
}
