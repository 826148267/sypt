package com.gzf.sypt.dao;

import com.gzf.sypt.POJO.PO.MachinePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author guozifan
 * @Description: 会IP编号表进行操作
 * @date 2018/6/11 18:01
 */
@Repository
public interface MachineDao extends BaseDao<MachinePO, String> {

    /**
     * 根据Ip获取机器序列号
     * @param machineIp 当前机器的Ip
     * @return Ip对应的序列号
     */
    @Override
    MachinePO getOne(@Param("machineIp") String machineIp);
}
