package com.ck.ai.dao.mapper;

import com.ck.ai.bean.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(username, email, password, first_name, last_name, avatar, phone_number, gender, date_of_birth, location, bio, is_active, is_admin, created_at, updated_at) VALUES(#{username}, #{email}, #{password}, #{firstName}, #{lastName}, #{avatar}, #{phoneNumber}, #{gender}, #{dateOfBirth}, #{location}, #{bio}, #{isActive}, #{isAdmin}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE username = #{usernameOrEmail} OR email = #{usernameOrEmail}")
    User selectUserByUsernameOrEmail(String usernameOrEmail);

    @Update("UPDATE user SET username = #{username}, email = #{email}, password = #{password}, first_name = #{firstName}, last_name = #{lastName}, avatar = #{avatar}, phone_number = #{phoneNumber}, gender = #{gender}, date_of_birth = #{dateOfBirth}, location = #{location}, bio = #{bio}, is_active = #{isActive}, is_admin = #{isAdmin}, updated_at = #{updatedAt} WHERE user_id = #{userId}")
    void updateUser(User user);
}

