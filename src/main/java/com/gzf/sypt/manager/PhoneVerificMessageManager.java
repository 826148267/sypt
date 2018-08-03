package com.gzf.sypt.manager;

import com.gzf.sypt.POJO.DTO.PhoneVerificManagerInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;

/**
 * @author guozifan
 * @Description: 手机验证码操作接口
 * @date 2018/7/28 22:50
 */
public interface PhoneVerificMessageManager extends VerificMessageManager<RegisterOperationOutputDTO, PhoneVerificManagerInputDTO> {

}
