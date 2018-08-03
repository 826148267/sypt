package com.gzf.sypt.manager.Impl;

import com.gzf.sypt.POJO.DO.RegisterQQMailVerificDO;
import com.gzf.sypt.POJO.DTO.QQMailVerificManagerInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.entity.QQMailVerificRegister;
import com.gzf.sypt.enums.RegisterStatusEnum;
import com.gzf.sypt.manager.QQMailVerificMessageManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author guozifan
 * @Description: 实现利用第三方接口进行验证信息的操作具体实现逻辑
 * @date 2018/7/28 18:53
 */
@Service
public class QQMailVerificMessageManagerImpl implements QQMailVerificMessageManager {

    @Override
    public RegisterOperationOutputDTO sendVerificCode(QQMailVerificManagerInputDTO qqMailVerificManagerInputDTO) {
        RegisterQQMailVerificDO registerQQMailVerificDO = new RegisterQQMailVerificDO();
        BeanUtils.copyProperties(qqMailVerificManagerInputDTO, registerQQMailVerificDO);
        if (registerQQMailVerificDO.sendVerificCode()){
            QQMailVerificRegister qqMailVerificRegister = new QQMailVerificRegister();
            BeanUtils.copyProperties(registerQQMailVerificDO, qqMailVerificRegister);
            return new RegisterOperationOutputDTO(RegisterStatusEnum.SEND_VERIFIC_SUCCESS, qqMailVerificRegister);
        }
        return new RegisterOperationOutputDTO(RegisterStatusEnum.SEND_VERIFIC_FAIL);
    }
}
