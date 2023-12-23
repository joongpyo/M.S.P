package com.example.md_exam.controller;

import com.example.md_exam.dto.CommentDto;
import com.example.md_exam.mapper.BoardQnaMapper;
import com.example.md_exam.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    BoardQnaMapper boardQnaMapper;

    @GetMapping("/comment/commentWrite")
    @ResponseBody
    public Map<String, Object> setCommentWrite(@ModelAttribute CommentDto commentDto){
        System.out.println(commentDto);
        commentMapper.updateCommentCnt(commentDto.getbIdFk());
        commentMapper.setComment(commentDto);
        return Map.of("msg","success");

    }

    @GetMapping("/comment/commentList")
    @ResponseBody
    public Map<String, Object> getCommentList(@ModelAttribute CommentDto commentDto){
        System.out.println(commentDto);
        List<CommentDto> clist = commentMapper.getCommentList(commentDto);
        return Map.of("cList",clist);
    }



}
