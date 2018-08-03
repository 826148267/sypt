package com.gzf.sypt.dao;

import com.gzf.sypt.BaseTest;
import com.gzf.sypt.POJO.PO.RegisterVerificPO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterVerificDaoTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(RegisterVerificDaoTest.class);

    @Autowired
    private RegisterVerificDao registerVerificDao;

    @Autowired
    private RegisterVerificPO registerVerificPO;

//    @Test
//    public void testInsert(){
//        long currentTime = System.currentTimeMillis();
//        registerVerific.setPhoneNum(13612203454L);
//        registerVerific.setCreateTime(new Date(currentTime));
//        registerVerific.setEndTime(new Date(currentTime + (60000 * 5)));
//        registerVerific.setVerific("123456");
//        long testTime = System.currentTimeMillis();
//        int i = registerVerificDao.insert(registerVerific);
//        logger.info(String.valueOf(System.currentTimeMillis() - testTime));
//        assertEquals(1,i);
//    }

    @Test
    public void testInsert(){
        long currentTime = System.currentTimeMillis();
        registerVerificPO.setMailBox("826148267@qq.com");
        registerVerificPO.setCreateTime(new Date(currentTime));
        registerVerificPO.setEndTime(new Date(currentTime + (60000 * 5)));
        registerVerificPO.setVerific("654321");
        long testTime = System.currentTimeMillis();
        int i = registerVerificDao.insert(registerVerificPO);
        logger.info(String.valueOf(System.currentTimeMillis() - testTime));
        assertEquals(1,i);
    }

//    @Test
//    public void testListAll(){
//        long currentTime = System.currentTimeMillis();
//        registerVerific.setMailBox("826148267@qq.com");
//        long testTime = System.currentTimeMillis();
//        List<RegisterVerific> registerVerifics = registerVerificDao.listAll(registerVerific);
//        logger.info(String.valueOf(System.currentTimeMillis() - testTime));
//        assertTrue(registerVerifics.get(0).getId() != null);
//    }

//    @Test
//    @Ignore
//    public void testListAll(){
//        long currentTime = System.currentTimeMillis();
//        registerVerific.setPhoneNum(13612203454L);
//        long testTime = System.currentTimeMillis();
//        List<RegisterVerific> registerVerifics = registerVerificDao.listAll(registerVerific);
//        logger.info(String.valueOf(System.currentTimeMillis() - testTime));
//        System.out.println(registerVerifics.size());
//        assertTrue(registerVerifics.get(0).getId() != null);
//    }
}
