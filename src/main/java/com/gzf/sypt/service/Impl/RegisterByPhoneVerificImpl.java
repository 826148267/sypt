package com.gzf.sypt.service.Impl;

import com.gzf.sypt.POJO.DO.RegisterPhoneVerificDO;
import com.gzf.sypt.dao.RegisterVerificDao;
import com.gzf.sypt.POJO.PO.RegisterVerificPO;
import com.gzf.sypt.POJO.DTO.*;
import com.gzf.sypt.enums.RegisterStatusEnum;
import com.gzf.sypt.manager.PhoneVerificMessageManager;
import com.gzf.sypt.service.RegisterByPhoneVerific;
import com.gzf.sypt.util.SecurityCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author guozifan
 * @Description: 实现通过手机号码注册具体业务逻辑
 * @date 2018/7/25 18:36
 */
@Component
public class RegisterByPhoneVerificImpl implements RegisterByPhoneVerific<RegisterOperationOutputDTO, PhoneVerificRegisterInputDTO> {

    @Autowired
    private RegisterVerificDao registerVerificDao;

    @Autowired
    private PhoneVerificMessageManager phoneVerificMessageManager;

    @Override
    public RegisterOperationOutputDTO requestVerificCode(PhoneVerificRegisterInputDTO phoneVerificRegisterInputDTO) throws IOException {
        RegisterPhoneVerificDO registerPhoneVerificDO = new RegisterPhoneVerificDO();
        BeanUtils.copyProperties(phoneVerificRegisterInputDTO, registerPhoneVerificDO);
        Map map = registerPhoneVerificDO.initParams();

        RegisterVerificPO registerVerificPO = new RegisterVerificPO();
        BeanUtils.copyProperties(registerPhoneVerificDO, registerVerificPO);

        // 插入手机号码，验证码摘要密文，创建时间，失效时间
        int i = registerVerificDao.insert(registerVerificPO);
        if (i == 1){
            // 如果数据库插入成功，设置验证码为明文
            registerVerificPO.setVerific(map.get("key").toString());

            PhoneVerificManagerInputDTO phoneVerificManagerInputDTO = new PhoneVerificManagerInputDTO();
            BeanUtils.copyProperties(registerVerificPO, phoneVerificManagerInputDTO);
            // 返回数据传输对象，并设置状态为验证码拉取成功
            return phoneVerificMessageManager.sendVerificCode(phoneVerificManagerInputDTO);
        }else{
            // 如果数据库插入失败，返回数据传输对象，并设置验证码拉取失败
            return new RegisterOperationOutputDTO(RegisterStatusEnum.REQUEST_VERIFIC_FAIL);
        }
    }

    @Override
    public RegisterOperationOutputDTO checkVerificCode(PhoneVerificRegisterInputDTO phoneVerificRegisterInputDTO) {
        // 将各种注册类型的账号拼接验证码变成明文来
        String plainText = String.valueOf(phoneVerificRegisterInputDTO.getPhoneNum()
                + phoneVerificRegisterInputDTO.getVerific());
        // 对明文进行MD5加密
        String cipherText = SecurityCodeUtil.getCipherText(plainText, "MD5");

        RegisterVerificPO registerVerificPO = new RegisterVerificPO();
        BeanUtils.copyProperties(phoneVerificRegisterInputDTO, registerVerificPO);
        registerVerificPO.setVerific(cipherText);

        // 从数据库从拉取所有符合条件的验证码集合
        List<RegisterVerificPO> registerVerificPOs = registerVerificDao.listAll(registerVerificPO);
        // 在集合中比对是否有符合的条目
        Iterator<RegisterVerificPO> iterator = registerVerificPOs.iterator();
        while (iterator.hasNext()){
            // 如果存在，则验证码正确，返回验证码正确状态传输对象
            if (cipherText.equals(String.valueOf(iterator.next().getVerific()))){
                return new RegisterOperationOutputDTO(RegisterStatusEnum.RIGHT_VERIFIC);
            }
        }
        // 遍历完集合之后还没有匹配项，则返回错误验证码状态传输对象
        return new RegisterOperationOutputDTO(RegisterStatusEnum.BAD_VERIFIC);
    }
}
