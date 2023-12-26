package com.example.project.service;

import com.example.project.dto.PageDto;
import com.example.project.dto.UserDto;
import com.example.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserDto setLogin(UserDto userDto){
        return userMapper.setLogin(userDto);

    }
    public void setRegister(UserDto userDto){
        userMapper.setRegister(userDto);
    }

    public PageDto PageInfo(int page,String searchType, String words) {
        PageDto pageDto = new PageDto();
        String searchQuery = getSearch(searchType,words);
        //전체 사용자 수
        int totalCount = userMapper.getUserCount(searchQuery);
        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage = ((int) (Math.ceil((double) page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        pageDto.setStartNum((page - 1) * pageDto.getPageCount());
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        return pageDto;
    }
    public List<UserDto> getUserList(int page,String searchType,String words ){
        PageDto pd = PageInfo(page,searchType,words);
        Map<String, Object> map = new HashMap<>();
        String searchQuery = getSearch(searchType,words);
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("searchQuery",searchQuery);
        return userMapper.getUserList(map);
    }
    public String getSearch(String searchType, String words){
        String searchQuery = "";
        if(searchType.equals("userId") ){
            searchQuery = " WHERE user_id = '"+words+"'";
        }else if(searchType.equals("userName")){
            searchQuery = " WHERE user_name = '"+words+"'";
        }else if(searchType.equals("userEmail")){
            searchQuery = " WHERE user_email LIKE '%"+words+"%'";
        }else {
            searchQuery = "";
        }
        return searchQuery;
    }


}
