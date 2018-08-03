package com.gzf.sypt.service;

import com.gzf.sypt.BaseTest;
import com.gzf.sypt.POJO.DTO.RegisterOperationInputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.PO.UserAuthPO;
import com.gzf.sypt.POJO.entity.PhoneVerificRegister;
import com.gzf.sypt.POJO.entity.QQMailVerificRegister;
import com.gzf.sypt.POJO.enums.RegisterTypeEnum;
import com.gzf.sypt.enums.RegisterStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author guozifan
 * @Description: 注册功能service层接口测试类
 * @date 2018/6/7 18:55
 */
public class RegisterServiceTest extends BaseTest {
    @Autowired
    @Qualifier("registerServiceImpl")
    private RegisterService registerService;

    @Autowired
    private UserAuthPO userAuthPO;

    @Test
    @Transactional
    public void testRegister() throws IOException {
//        RegisterOperationInputDTO registerOperationInputDTO = new RegisterOperationInputDTO();
//        PhoneVerificRegister phoneVerificRegister = new PhoneVerificRegister();
//        phoneVerificRegister.setPhoneNum(13612203454L);
//        registerOperationInputDTO.setRegisterType(RegisterTypeEnum.REQUEST_VERIFIC_PHONE.getStatus());
//        registerOperationInputDTO.setVo(phoneVerificRegister);
//
//        RegisterOperationOutputDTO registerOperationOutputDTO =
//                (RegisterOperationOutputDTO) registerService.register(registerOperationInputDTO);
//        if (registerOperationOutputDTO.getStatus().equals(RegisterStatusEnum.SEND_VERIFIC_SUCCESS.getStatus())){
//            RegisterOperationInputDTO registerOperationInputDTO1 = new RegisterOperationInputDTO();
//            PhoneVerificRegister phoneVerificRegister1 = new PhoneVerificRegister();
//            phoneVerificRegister1.setPhoneNum(13612203454L);
//            phoneVerificRegister1.setVerific(((PhoneVerificRegister)registerOperationOutputDTO.getEntity()).getVerific());
//            registerOperationInputDTO1.setRegisterType(RegisterTypeEnum.CHECK_VERIFIC_PHONE.getStatus());
//            registerOperationInputDTO1.setVo(phoneVerificRegister1);
//            registerOperationOutputDTO = (RegisterOperationOutputDTO) registerService.register(registerOperationInputDTO1);
//        }
//        assertTrue(registerOperationOutputDTO != null);
        RegisterOperationInputDTO registerOperationInputDTO = new RegisterOperationInputDTO();
        QQMailVerificRegister qqMailVerificRegister = new QQMailVerificRegister();
        qqMailVerificRegister.setMailBox("283956614@qq.com");
        registerOperationInputDTO.setRegisterType(RegisterTypeEnum.REQUEST_VERIFIC_QQMAIL.getStatus());
        registerOperationInputDTO.setVo(qqMailVerificRegister);

        RegisterOperationOutputDTO registerOperationOutputDTO =
                (RegisterOperationOutputDTO) registerService.register(registerOperationInputDTO);
        if (registerOperationOutputDTO.getStatus().equals(RegisterStatusEnum.SEND_VERIFIC_SUCCESS.getStatus())){
            RegisterOperationInputDTO registerOperationInputDTO1 = new RegisterOperationInputDTO();
            QQMailVerificRegister qqMailVerificRegister1 = new QQMailVerificRegister();
            qqMailVerificRegister1.setMailBox("283956614@qq.com");
            qqMailVerificRegister1.setVerific(((QQMailVerificRegister) registerOperationOutputDTO.getEntity()).getVerific());
            System.out.println("验证码为可能会错:" + qqMailVerificRegister1.getVerific());
            registerOperationInputDTO1.setRegisterType(RegisterTypeEnum.CHECK_VERIFIC_QQMAIL.getStatus());
            registerOperationInputDTO1.setVo(qqMailVerificRegister1);
            registerOperationOutputDTO = (RegisterOperationOutputDTO) registerService.register(registerOperationInputDTO1);
        }
        assertTrue(registerOperationOutputDTO != null);
    }

//    @Test
//    public void testInsertUserAuth() {
//        userAuthPO.setLoginName(String.valueOf(18127491159L));
//        userAuthPO.setCertificate("ggguozifan");
//        userAuthPO.setUserType(UserTypeEnum.CONSIGNOR.getStatus());
//        userAuthPO.setLoginType(LoginTypeEnum.BY_PHONE_VERIFIC.getStatus());
//        userAuthPO.setOnlineStatus(OnlineStatusEnum.ONLINE.getStatus());
//        try {
//            registerService.insertUserAuth(userAuthPO);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        System.out.println("userId：" + userAuthPO.getUserId());
//        assertTrue(userAuthPO.getId() != null);
//    }
}
