package com.ck.ai.service;

import com.ck.ai.domain.entity.User;
import com.ck.ai.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User registerUser(User user) {
        // 检查用户名和邮箱是否已被注册
        if (userMapper.selectUserByUsernameOrEmail(user.getUsername()) != null) {
            throw new RuntimeException("用户名已被注册");
        }
        if (userMapper.selectUserByUsernameOrEmail(user.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        // 对密码进行加密处理
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置创建时间和更新时间
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        // 插入用户信息
        userMapper.insertUser(user);
        return user;
    }

    @Override
    public User loginUser(String usernameOrEmail, String password) {
        // 根据用户名或邮箱查询用户信息
        User user = userMapper.selectUserByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        // 验证密码是否正确
        if(!Objects.equals(password, user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return user;
    }
}


