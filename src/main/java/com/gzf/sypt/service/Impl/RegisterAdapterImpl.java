package com.gzf.sypt.service.Impl;

import com.gzf.sypt.POJO.DTO.PhoneVerificRegisterInputDTO;
import com.gzf.sypt.POJO.DTO.QQMailVerificRegisterInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationInputDTO;
import com.gzf.sypt.POJO.entity.PhoneVerificRegister;
import com.gzf.sypt.POJO.entity.QQMailVerificRegister;
import com.gzf.sypt.POJO.enums.RegisterTypeEnum;
import com.gzf.sypt.enums.RegisterStatusEnum;
import com.gzf.sypt.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author guozifan
 * @Description: 注册接口适配器
 * @date 2018/6/7 0:35
 */
@Component
public class RegisterAdapterImpl implements RegisterAdapter<RegisterOperationOutputDTO, RegisterOperationInputDTO> {
    private AnywayRegister anywayRegister;

    @Autowired
    private RegisterByQQMailVerific registerByQQMailVerific;

    @Autowired
    private RegisterByPhoneVerific registerByPhoneVerific;

    /**
     * 根据不同类型注入不同实体对象
     * @param registerType 注册方式
     */
    @Override
    public void setAnywayRegister(Integer registerType) {
        if (registerType.equals(RegisterTypeEnum.REQUEST_VERIFIC_QQMAIL.getStatus()) ||
                registerType.equals(RegisterTypeEnum.CHECK_VERIFIC_QQMAIL.getStatus())){
            anywayRegister = registerByQQMailVerific;
        }else if (registerType.equals(RegisterTypeEnum.REQUEST_VERIFIC_PHONE.getStatus()) ||
                registerType.equals(RegisterTypeEnum.CHECK_VERIFIC_PHONE.getStatus())){
            anywayRegister = registerByPhoneVerific;
        }else {
            throw new RuntimeException("注册类型有误，类型为" + registerType + "，发生在"
                    + this.getClass().getSimpleName());
        }
    }

    /**
     * 根据不同实体对象进行不同函数操作
     * @param registerOperationInputDTO 属性携带注册方式
     * @return 返回数据传输对象，返回注册操作对应的状态及状态信息
     */
    @Override
    public RegisterOperationOutputDTO register(RegisterOperationInputDTO registerOperationInputDTO) throws IOException {
        RegisterOperationOutputDTO registerOperationOutputDTO = null;
        if (registerOperationInputDTO.getRegisterType().equals(RegisterTypeEnum.REQUEST_VERIFIC_PHONE.getStatus())){
            PhoneVerificRegister phoneVerificRegister = (PhoneVerificRegister) registerOperationInputDTO.getVo();
            PhoneVerificRegisterInputDTO phoneVerificRegisterInputDTO = new PhoneVerificRegisterInputDTO();
            BeanUtils.copyProperties(phoneVerificRegister, phoneVerificRegisterInputDTO);
            // 请求手机验证码
            registerOperationOutputDTO = (RegisterOperationOutputDTO) ((RegisterByVerific) anywayRegister).requestVerificCode(phoneVerificRegisterInputDTO);
        }else if (registerOperationInputDTO.getRegisterType().equals(RegisterTypeEnum.CHECK_VERIFIC_PHONE.getStatus())) {
            PhoneVerificRegister phoneVerificRegister = (PhoneVerificRegister) registerOperationInputDTO.getVo();
            PhoneVerificRegisterInputDTO phoneVerificRegisterInputDTO = new PhoneVerificRegisterInputDTO();
            BeanUtils.copyProperties(phoneVerificRegister, phoneVerificRegisterInputDTO);
            // 检验手机验证码
            registerOperationOutputDTO =
                    (RegisterOperationOutputDTO) ((RegisterByVerific) anywayRegister).checkVerificCode(phoneVerificRegisterInputDTO);
        }else if (registerOperationInputDTO.getRegisterType().equals(RegisterTypeEnum.REQUEST_VERIFIC_QQMAIL.getStatus())) {
            QQMailVerificRegister qqMailVerificRegister = (QQMailVerificRegister) registerOperationInputDTO.getVo();
            QQMailVerificRegisterInputDTO qqMailVerificRegisterInputDTO = new QQMailVerificRegisterInputDTO();
            BeanUtils.copyProperties(qqMailVerificRegister, qqMailVerificRegisterInputDTO);
            // 请求邮箱验证码
            registerOperationOutputDTO = (RegisterOperationOutputDTO) ((RegisterByVerific) anywayRegister).requestVerificCode(qqMailVerificRegisterInputDTO);
        }else if (registerOperationInputDTO.getRegisterType().equals(RegisterTypeEnum.CHECK_VERIFIC_QQMAIL.getStatus())){
            QQMailVerificRegister qqMailVerificRegister = (QQMailVerificRegister) registerOperationInputDTO.getVo();
            QQMailVerificRegisterInputDTO qqMailVerificRegisterInputDTO = new QQMailVerificRegisterInputDTO();
            BeanUtils.copyProperties(qqMailVerificRegister, qqMailVerificRegisterInputDTO);
            // 检验邮箱验证码
            registerOperationOutputDTO = (RegisterOperationOutputDTO) ((RegisterByVerific) anywayRegister).checkVerificCode(qqMailVerificRegisterInputDTO);
        }else {
            registerOperationOutputDTO = new RegisterOperationOutputDTO(RegisterStatusEnum.UNKNOWN_REGISTER_TYPE);
        }
        return registerOperationOutputDTO;
    }
}
