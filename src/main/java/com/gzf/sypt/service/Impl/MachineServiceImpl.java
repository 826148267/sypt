package com.gzf.sypt.service.Impl;

import com.gzf.sypt.dao.MachineDao;
import com.gzf.sypt.POJO.PO.MachinePO;
import com.gzf.sypt.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author guozifan
 * @Description: 管理当前服务器主机的相关信息
 * @date 2018/6/11 23:25
 */
@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineDao machineDao;

    @Override
    public int getMachineId() {
        //获取本机的Ip地址
        String inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("获取主机号失败，错误发生在" + this.getClass().getSimpleName());
        }
        //获取到本机Ip对应的序列号
        MachinePO machinePO = machineDao.getOne(inetAddress);
        //如果没有获取到
        if (machinePO.getId() == null){
            //执行插入新Ip并返回序列号操作
            machinePO.setMachineIp(inetAddress);
            machineDao.insert(machinePO);
        }
        return machinePO.getId();
    }
}
