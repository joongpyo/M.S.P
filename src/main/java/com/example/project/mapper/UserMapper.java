package com.example.project.mapper;

import com.example.project.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user VALUES (NULL,#{userId},#{userPasswd},#{userName}, #{userEmail})")
    public void setRegister(UserDto userDto);

    @Select("SELECT * FROM user WHERE user_id = #{userId} AND user_passwd = #{userPasswd}")
    public UserDto setLogin(UserDto userDto);

    @Select("SELECT * FROM user ${searchQuery} ORDER BY u_id DESC LIMIT #{startNum}, #{offset}")
    public List<UserDto> getUserList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM user ${searchQuery}")
    public int getUserCount(String searchQuery);

    @Select("SELECT * FROM user ${searchQuery}")
    public UserDto getFindUser(String searchQuery);
}

