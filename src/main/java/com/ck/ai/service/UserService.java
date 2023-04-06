package com.ck.ai.service;

import com.ck.ai.domain.entity.User;
import com.ck.ai.domain.exception.FailRequestException;
import com.ck.ai.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2021/11/12
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public String getPassword(String username) {
        return Optional.ofNullable(userMapper.getPassword(username)).orElseThrow(() -> new FailRequestException("用户不存在"));
    }

    public String getRole(String username) {
        return userMapper.getRole(username);
    }

    public String getRolePermission(String username) {
        return userMapper.getRolePermission(username);
    }

    public List<String> getPermission(String username) {
        return userMapper.getPermission(username);
    }

    public void login(String username, String password) {
        String p = getPassword(username);
        if (!p.equals(password)) {
            throw new FailRequestException("密码错误");
        }
    }

    public void auth(String username) {
        int count = userMapper.auth(username);
        if (count == 0) {
            throw new FailRequestException("用户不存在或用户被禁用");
        }
    }

    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public int updateUserByUsername(User user) {
        return userMapper.updateUserByUsername(user);
    }
}