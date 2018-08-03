package com.gzf.sypt.service.Impl;

import com.gzf.sypt.POJO.DTO.RegisterOperationOutputDTO;
import com.gzf.sypt.POJO.DTO.RegisterOperationInputDTO;
import com.gzf.sypt.service.RegisterAdapter;
import com.gzf.sypt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author guozifan
 * @Description: 调用适配器，实现注册功能
 * @date 2018/6/6 22:22
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService<RegisterOperationOutputDTO, RegisterOperationInputDTO> {

    @Autowired
    private RegisterAdapter registerAdapter;

    /**
     * 通过适配器进行注册业务
     * @param registerOperationInputDTO
     * @return
     * @throws IOException
     */
    @Override
    public RegisterOperationOutputDTO register(RegisterOperationInputDTO registerOperationInputDTO) throws IOException {
        // 通过注册类型注入对应的实现类
        registerAdapter.setAnywayRegister(registerOperationInputDTO.getRegisterType());
        // 并使用该类型的对象进行注册操作
        return (RegisterOperationOutputDTO) registerAdapter.register(registerOperationInputDTO);
    }
}
