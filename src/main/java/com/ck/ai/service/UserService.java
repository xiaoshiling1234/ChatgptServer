package com.ck.ai.service;

import com.ck.ai.dao.entity.User;
import com.ck.ai.dao.entity.UserExample;
import com.ck.ai.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<com.ck.ai.dao.entity.User> findByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }

    public void registerUser(User user) {
        List<com.ck.ai.dao.entity.User> byUsername = this.findByUsername(user.getUsername());
        if (byUsername != null) {
            throw new RuntimeException("Username already exists");
        }
        userMapper.insert(user);
    }
}

