package com.gzf.sypt.manager.Impl;

import com.alibaba.fastjson.JSONObject;
import com.gzf.sypt.POJO.DO.RegisterPhoneVerificDO;
import com.gzf.sypt.POJO.DTO.PhoneVerificManagerInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.entity.PhoneVerificRegister;
import com.gzf.sypt.enums.RegisterStatusEnum;
import com.gzf.sypt.manager.PhoneVerificMessageManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.io.*;

/**
 * @author guozifan
 * @Description: 手机验证码操作实现类
 * @date 2018/7/28 22:52
 */
@Service
public class PhoneVerificMessageManagerImpl implements PhoneVerificMessageManager {

    @Override
    public RegisterOperationOutputDTO sendVerificCode(PhoneVerificManagerInputDTO phoneVerificManagerInputDTO) throws IOException {
        RegisterPhoneVerificDO registerPhoneVerificDO = new RegisterPhoneVerificDO();
        BeanUtils.copyProperties(phoneVerificManagerInputDTO, registerPhoneVerificDO);
        JSONObject jsonObject = registerPhoneVerificDO.sendVerificCode();
        if ("00000".equals(jsonObject.get("respCode"))){
            PhoneVerificRegister phoneVerificRegister = new PhoneVerificRegister();
            BeanUtils.copyProperties(registerPhoneVerificDO, phoneVerificRegister);
            return new RegisterOperationOutputDTO(RegisterStatusEnum.SEND_VERIFIC_SUCCESS, phoneVerificRegister);
        }else {
            return new RegisterOperationOutputDTO(RegisterStatusEnum.SEND_VERIFIC_FAIL);
        }
    }
}
