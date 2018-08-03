package com.gzf.sypt.dao;

import com.gzf.sypt.BaseTest;
import com.gzf.sypt.POJO.PO.UserAuthPO;
import com.gzf.sypt.POJO.enums.LoginTypeEnum;
import com.gzf.sypt.POJO.enums.OnlineStatusEnum;
import com.gzf.sypt.POJO.enums.UserTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserAuthPODaoTest extends BaseTest {
    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private UserAuthPO userAuthPO;

    @Test
    public void testInsert(){
        userAuthPO.setUserId(18127491159L);
        userAuthPO.setUserType(UserTypeEnum.CONSIGNOR.getStatus());
        userAuthPO.setLoginType(LoginTypeEnum.BY_PHONE_VERIFIC.getStatus());
        userAuthPO.setLoginName("13612203454L");
        userAuthPO.setCertificate("123456789");
        userAuthPO.setCreateTime(new Date(System.currentTimeMillis()));
        userAuthPO.setLastLoginTime(new Date(System.currentTimeMillis()));
        userAuthPO.setOnlineStatus(OnlineStatusEnum.ONLINE.getStatus());
        int i = userAuthDao.insert(userAuthPO);
        assertEquals(1,i);
    }

//    @Test
//    public void testUpdate(){
//        userAuth.setUserId("18127491159");
//        userAuth.setCertificate("1234567");
//        int i = userAuthDao.update(userAuth);
//        System.out.println(userAuth.getCertificate());
//        assertEquals(1, i);
//    }
}
