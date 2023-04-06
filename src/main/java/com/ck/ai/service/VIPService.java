package com.ck.ai.service;

import com.ck.ai.domain.entity.User;
import com.ck.ai.domain.enums.RoleEnum;
import com.ck.ai.mapper.UserMapper;
import com.ck.ai.util.RoleDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class VIPService {
    @Autowired
    UserMapper userMapper;
    public int registerVIP(String username, RoleEnum roleEnum) {
        // 注册 VIP 的具体实现，例如向数据库中插入会员信息等
        Date expireDate = RoleDateUtil.calculateExpireDate(roleEnum);
        User user = new User();
        user.setRole(roleEnum);
        user.setExpireDate(expireDate);
        user.setUsername(username);
        return userMapper.updateUserByUsername(user);
    }
}

