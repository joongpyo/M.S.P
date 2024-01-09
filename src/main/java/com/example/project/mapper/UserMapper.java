package com.example.project.mapper;

import com.example.project.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user VALUES (NULL,#{userId},#{userPasswd},#{userName}, #{userEmail}, now())")
    public void setRegister(UserDto userDto);

    @Select("SELECT * FROM user WHERE user_id = #{userId} AND user_passwd = #{userPasswd}")
    public UserDto setLogin(UserDto userDto);
    @Select("SELECT COUNT(*) FROM user WHERE user_id = #{userId} ")
    public int getCheckUserId(String userId);
    @Select("SELECT COUNT(*) FROM user WHERE user_email = #{userEmail}")
    public int getCheckUserEmail(String userEmail);
    @Select("SELECT * FROM user ${searchQuery} ORDER BY u_id DESC LIMIT #{startNum}, #{offset}")
    public List<UserDto> getUserList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM user ${searchQuery}")
    public int getUserCount(String searchQuery);

    @Select("SELECT * FROM user ${searchQuery}")
    public UserDto getFindUser(String searchQuery);
    @Select("SELECT COUNT(*) FROM user")
    public int getTotalCount();
    @Select("SELECT COUNT(*) FROM user WHERE DATE(user_reg) = CURDATE()")
    public int getTodayCount();
    @Delete("delete from user where u_id = #{uId}")
    public void deleteUser(UserDto userDto);

    @Update("update user set user_passwd = #{userPasswd} where u_id = #{uId}")
    public void updateUser(UserDto userDto);
    //userDelete
    @Delete("DELETE FROM user WHERE u_id = ${uId}")
    public void userDelete(UserDto userDto);
}

