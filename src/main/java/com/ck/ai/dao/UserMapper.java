package com.ck.ai.dao;

import com.ck.ai.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);

    void insert(User user);
}
