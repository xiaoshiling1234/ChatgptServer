package com.ck.ai.mapper;

import com.ck.ai.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2021/11/11
 */
@Mapper
public interface UserMapper {
    /**
     * 获得密码
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     */
    String getRole(String username);


    /**
     * 获得用户角色默认的权限
     */
    String getRolePermission(String username);

    /**
     * 获得用户的权限
     */
    List<String> getPermission(String username);

    /**
     * 用户登录
     */
    int auth(String username);
    User selectByUsername(String username);
    User selectByEmail(String email);
    int insert(User user);
    int updateUserByUsername(User user);

    int updateFreeChatTime();
}

