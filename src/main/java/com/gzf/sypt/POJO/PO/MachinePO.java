package com.gzf.sypt.POJO.PO;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author guozifan
 * @Description: 服务器信息领域对象
 * @date 2018/6/11 18:20
 */
@Component
public class MachinePO implements Serializable {
    private Integer id;
    private String machineIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", machineIp='" + machineIp + '\'' +
                '}';
    }
}
