package com.gzf.sypt.service.Impl;

import com.gzf.sypt.POJO.DTO.QQMailVerificRegisterInputDTO;
import com.gzf.sypt.dao.RegisterVerificDao;
import com.gzf.sypt.POJO.DO.RegisterQQMailVerificDO;
import com.gzf.sypt.POJO.DTO.QQMailVerificManagerInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.PO.RegisterVerificPO;
import com.gzf.sypt.enums.RegisterStatusEnum;
import com.gzf.sypt.manager.QQMailVerificMessageManager;
import com.gzf.sypt.service.RegisterByQQMailVerific;
import com.gzf.sypt.util.SecurityCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author guozifan
 * @Description: 实现通过QQ邮箱验证码完成注册业务的业务逻辑
 * @date 2018/7/25 18:50
 */
@Component
public class RegisterByQQMailVerificImpl implements RegisterByQQMailVerific<RegisterOperationOutputDTO, QQMailVerificRegisterInputDTO> {

    @Autowired
    private RegisterVerificDao registerVerificDao;

    @Autowired
    private QQMailVerificMessageManager qQMailVerificMessageManager;

    @Override
    public RegisterOperationOutputDTO requestVerificCode(QQMailVerificRegisterInputDTO qqMailVerificRegisterInputDTO) throws IOException {
        RegisterQQMailVerificDO registerQQMailVerificDO = new RegisterQQMailVerificDO();
        BeanUtils.copyProperties(qqMailVerificRegisterInputDTO, registerQQMailVerificDO);

        Map map = registerQQMailVerificDO.initParams();

        RegisterVerificPO registerVerificPO = new RegisterVerificPO();
        BeanUtils.copyProperties(registerQQMailVerificDO, registerVerificPO);

        int i = registerVerificDao.insert(registerVerificPO);   // 将验证码信息插入数据库

        if (i == 1){
            // 如果数据库插入成功，设置验证码为明文
            registerQQMailVerificDO.decodeVerific(map);

            QQMailVerificManagerInputDTO qqMailVerificManagerInputDTO = new QQMailVerificManagerInputDTO();
            BeanUtils.copyProperties(registerQQMailVerificDO, qqMailVerificManagerInputDTO);
            return qQMailVerificMessageManager.sendVerificCode(qqMailVerificManagerInputDTO);
        }else{
            // 如果数据库插入失败，返回数据传输对象，并设置验证码拉取失败
            return new RegisterOperationOutputDTO(RegisterStatusEnum.REQUEST_VERIFIC_FAIL);
        }
    }

    @Override
    public RegisterOperationOutputDTO checkVerificCode(QQMailVerificRegisterInputDTO qqMailVerificRegisterInputDTO) {
        // 将各种注册类型的账号拼接验证码变成明文来
        String plainText = String.valueOf(qqMailVerificRegisterInputDTO.getMailBox() + qqMailVerificRegisterInputDTO.getVerific());
        // 对明文进行MD5加密
        String cipherText = SecurityCodeUtil.getCipherText(plainText, "MD5");

        RegisterVerificPO registerVerificPO = new RegisterVerificPO();
        BeanUtils.copyProperties(qqMailVerificRegisterInputDTO, registerVerificPO);
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
