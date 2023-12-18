package com.example.md_exam.controller;

import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.mapper.BoardQnaMapper;
import com.example.md_exam.service.BoardQnaService;
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
    public String getBoardQnA(){
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
    public String getBoardView(@RequestParam int qnaId, Model model) {
        model.addAttribute("board",boardQnaService.getQnaView(qnaId));
        model.addAttribute("files",boardQnaMapper.getFile(qnaId));
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
            int fileID = qnaDto.getQnaId();
            boardQnaService.setFiles(files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "qnaId") 사용
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setBoard(qnaDto);
        }
        return Map.of("msg","success");
    }

    @GetMapping("/qnaList")
    @ResponseBody
    public Map<String,Object> getQnaList(){
        List<QnaDto> list = boardQnaService.getQnaList();
        return Map.of("qnaList",list);
    }

    @GetMapping("/qnaDelete")
    public String setDelete(@RequestParam int qnaId) {
        System.out.println(qnaId);
        boardQnaService.setDelete(qnaId);
        return "redirect:/board/boardQna";
    }

    @GetMapping("/qnaUpdate")
    public String getUpdate(@RequestParam int qnaId, Model model) {
        QnaDto qd = boardQnaService.getQnaView(qnaId);
        model.addAttribute("modify",qd);
        return "board/qnaUpdate";
    }

    @PostMapping("/qnaUpdate")
    @ResponseBody
    public Map<String, Object> setUpdate(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                         @ModelAttribute QnaDto qnaDto) throws IOException {

        //파일수정
        //파일 있으면 파일 추가
        if (files != null){
            qnaDto.setIsFiles("Y");
            boardQnaService.setUpdate(qnaDto);

            int fileID = qnaDto.getQnaId();
            boardQnaService.setFiles(files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "qnaId") 사용
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setUpdate(qnaDto);
        }

        return null;
    }

}
