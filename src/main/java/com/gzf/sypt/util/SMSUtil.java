package com.gzf.sypt.util;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guozifan
 * @Description: 短信发送工具类
 * @date 2018/8/2 17:32
 */
public class SMSUtil {
    // 时间戳格式为yyyyMMddHHmmss
    private static String timestamp = getTimeStamp();

    // 接口地址
    private static final String INTERF_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    // 接口平台账户ID
    private static final String ACCOUNT_SID = "42728645fa0e4c989a1eefec3440d43c";

    // 平台auth_token口令
    private static final String AUTH_TOKEN = "f89c16754bce4350ba7dfb05ff669c25";

    // sig = MD5（ACCOUNT_SID+AUTH_TOKEN+timestamp）
    private static String sig = SecurityCodeUtil.getCipherText(ACCOUNT_SID + AUTH_TOKEN + timestamp, "MD5");

    // 短信内容模板ID
    private static String registerTemplateid = "474967875";

    // 短信内容模板ID
    private static String loginTemplateid = "477225835";

    // 验证码超时时间
    private static final String TIMEOUT = "5";

    public static JSONObject sendVerificCode(String phoneNum, String verific) throws IOException {
        String params = "accountSid=" + ACCOUNT_SID + "&templateid=" +
                registerTemplateid + "&param=" + verific + "," + TIMEOUT + "&to=" +
                phoneNum + "&timestamp=" + timestamp + "&sig=" + sig;
        JSONObject jsonObject = HttpUtil.getPostResult(INTERF_URL, params);
        return jsonObject;
    }

    /**
     * 生产yyyyMMddHHmmss格式的时间戳
     * @return 字符串格式的时间戳yyyyMMddHHmmss
     */
    private static String getTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());
        return timestamp;
    }
}
