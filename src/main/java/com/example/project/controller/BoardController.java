package com.example.project.controller;

import com.example.project.dto.QnaDto;
import com.example.project.mapper.BoardQnaMapper;
import com.example.project.service.BoardQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardQnaService boardQnaService;
    @Autowired
    BoardQnaMapper boardQnaMapper;

    @GetMapping("/boardNotice")
    public String getBoardNotice(){
        return "board/boardNotice";
    }

    @GetMapping("/boardQnA")
    public String getBoardQnA(Model model,
                              @RequestParam(value="page", defaultValue = "1")int page,
                              @RequestParam(value="searchType", defaultValue = "") String searchType,
                              @RequestParam(value="search", defaultValue = "") String search){

        model.addAttribute("qna",boardQnaService.getBoardQnA(page,searchType,search));
        model.addAttribute("page",boardQnaService.PageInfo(page, searchType, search));
        model.addAttribute("total",boardQnaService.getBoardCount(searchType,search));
        return "board/boardQnA";
    }

    @GetMapping("/boardList")
    public String getBoardList(){
        return "board/boardList";
    }

    @GetMapping("/boardReview")
    public String getBoardReview(){
        return "board/boardReview";
    }

    @GetMapping("/boardView")
    public String getBoardView(@RequestParam int id, Model model) {
        model.addAttribute("board",boardQnaService.getQnaView(id));
        model.addAttribute("files",boardQnaMapper.getFile(id));
        //조회수증가
        boardQnaMapper.updateVisit(id);
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    @ResponseBody
    public Map<String, Object> setBoardWrite(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                             @ModelAttribute QnaDto qnaDto) throws IOException {
        //파일 저장
        if (files != null){
            qnaDto.setIsFiles("Y");
            boardQnaService.setBoard(qnaDto);
            int fileID = qnaDto.getId();
            boardQnaService.setFiles(files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "id") 사용
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setBoard(qnaDto);
        }
        return Map.of("msg","success");
    }

    @GetMapping("/qnaDelete")
    public String setDelete(@RequestParam int id) {
        System.out.println(id);
        boardQnaService.setDelete(id);
        return "redirect:/board/boardQnA";
    }

    //수정하러가기
    @GetMapping("/qnaUpdate")
    public String getUpdate(@RequestParam int id, Model model) {
        QnaDto qd = boardQnaService.getQnaView(id);
        model.addAttribute("modify",qd);
        return "board/qnaUpdate";
    }

    //수정등록하기
    @PostMapping("/qnaUpdate")
    @ResponseBody
    public Map<String, Object> setUpdate(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                         @ModelAttribute QnaDto qnaDto) throws IOException {
        //파일수정
        //파일 있으면 파일 추가
        if (files != null){
            qnaDto.setIsFiles("Y");
            boardQnaService.setUpdate(qnaDto);

            int fileID = qnaDto.getId();
            boardQnaService.setFiles(files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "id") 사용
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setUpdate(qnaDto);
        }

        return Map.of("msg","success");
    }

    @GetMapping("/boardReply")
    public String getReply(@RequestParam int id, Model model){
        QnaDto qnaDto = boardQnaService.getQnaView(id);
        model.addAttribute("reply", qnaDto);
        return "board/boardReply";
    }




}
