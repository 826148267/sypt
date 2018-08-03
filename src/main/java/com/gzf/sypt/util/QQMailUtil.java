package com.gzf.sypt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author guozifan
 * @Description: 邮箱操作工具类
 * @date 2018/8/2 17:58
 */
@Component
public class QQMailUtil {

    private static SimpleMailMessage simpleMailMessage;

    private static JavaMailSender javaMailSender;

    @Autowired
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage){
        QQMailUtil.simpleMailMessage = simpleMailMessage;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender){
        QQMailUtil.javaMailSender = javaMailSender;
    }

    /**
     * 设置参数并发送邮件
     * @param mailBox
     * @param verific
     * @return
     */
    public static boolean sendVerificCode(String mailBox, String verific) {
        boolean flag = true;
        simpleMailMessage.setTo(mailBox);  // 邮件的目的地
        simpleMailMessage.setSubject("代练帮邮箱账号注册验证码"); // 设置邮件的标题
        simpleMailMessage.setText("尊敬的用户，您的验证码为" + verific + ",请勿轻易外泄!");
        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            flag = false;
            throw new RuntimeException("邮件发送失败");
        }finally {
            return flag;
        }
    }
}
