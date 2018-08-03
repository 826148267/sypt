package com.gzf.sypt.manager;

import com.gzf.sypt.POJO.DTO.QQMailVerificManagerInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;

/**
 * @author guozifan
 * @Description: 第三方接口进行邮件验证信息接口
 * @date 2018/7/28 18:50
 */
public interface QQMailVerificMessageManager
        extends VerificMessageManager<RegisterOperationOutputDTO, QQMailVerificManagerInputDTO> {

}
