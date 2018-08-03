package com.gzf.sypt.service;


/**
 * @author guozifan
 * @Description: 获取当前主机的相关信息
 * @date 2018/6/11 22:41
 */
public interface MachineService {
    /**
     * 根据IP获取机器序号
     * @return 返回int型
     */
    int getMachineId();
}
