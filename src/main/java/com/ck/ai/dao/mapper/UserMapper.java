package com.ck.ai.dao.mapper;

import com.ck.ai.bean.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);
}
