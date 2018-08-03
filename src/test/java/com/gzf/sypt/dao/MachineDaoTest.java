package com.gzf.sypt.dao;

import com.gzf.sypt.BaseTest;
import com.gzf.sypt.POJO.PO.MachinePO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author guozifan
 * @Description: 主机信息持久层处理对象
 * @date 2018/8/1 16:39
 */
public class MachineDaoTest extends BaseTest {

    @Autowired
    private MachineDao machineDao;

    @Test
    public void testInsert(){
        MachinePO machinePO = new MachinePO();
        machinePO.setMachineIp("10.0.0.111");
        int i = machineDao.insert(machinePO);
        assertEquals(1, i);
    }

}
