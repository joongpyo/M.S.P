package com.example.controller;

import com.example.dto.CommentDto;
import com.example.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/comment/commentWrite")
    @ResponseBody
    public Map<String, Object> setCommentWrite(@RequestParam String configCode,
                                               @ModelAttribute CommentDto commentDto){

        commentMapper.updateCommentCnt(configCode,commentDto.getbIdFk());
        commentDto.setConfigCode(configCode);
        commentMapper.setComment(commentDto);
        return Map.of("msg","success");

    }

    @GetMapping("/comment/commentList")
    @ResponseBody
    public Map<String, Object> getCommentList(@RequestParam String configCode,
                                              @ModelAttribute CommentDto commentDto){
        commentDto.setConfigCode(configCode);
        List<CommentDto> clist = commentMapper.getCommentList(commentDto);
        return Map.of("cList",clist);
    }



}
