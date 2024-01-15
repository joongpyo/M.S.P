package com.example.project.controller;

import com.example.project.dto.BoardDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import com.example.project.mapper.BoardMapper;
import com.example.project.mapper.MedicineMapper;
import com.example.project.service.BoardService;
import com.example.project.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Value("${fileDir}")
    String fileDir;
//    수정
    @Autowired
    BoardService boardService;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    MedicineService medicineService;
    @Autowired
    MedicineMapper medicineMapper;

    //231223 board코드 추가 jang
    //게시판 목록가져올때
    @GetMapping("/board")
    public String getBoard(Model model,
                              @RequestParam String configCode,
                              @RequestParam(value="page", defaultValue = "1") int page,
                              @RequestParam(value="searchType", defaultValue = "") String searchType,
                              @RequestParam(value="search", defaultValue = "") String search){

        if(configCode.equals("List")){
            model.addAttribute("files",medicineService.getFilesAll());
            model.addAttribute("medicines",medicineService.getMedList(page,searchType,search));
            model.addAttribute("page",medicineService.PageInfo(page, searchType, search));
            model.addAttribute("total",medicineMapper.getMedCount(medicineService.getMedSearch(searchType,search)));
            model.addAttribute("configCode",configCode);
            if(search.equals("전체"))
                search="";
            model.addAttribute("search",search);
        }else{
            model.addAttribute("board",boardService.getBoard(configCode,page,searchType,search));
            model.addAttribute("page",boardService.PageInfo(configCode,page, searchType, search));
            model.addAttribute("total",boardService.getBoardCount(configCode,searchType,search));
            model.addAttribute("configCode",configCode);
        }

        String board;
        switch (configCode) {
            case "QnA" :
                board = "board/boardQnA";
                break;
            case "Notice":
                board = "board/boardNotice";
                break;
            case "Review":
                board = "board/boardReview";
                break;
            case "List":
                board = "board/boardList";
                break;
            default:
                board = "index";
                break;
        }
        return board;
    }

    @PostMapping("/board")
    @ResponseBody
    public  Map<String,Object> getBoardList(@RequestParam String configCode,
                               @RequestParam(value="page", defaultValue = "1") int page,
                               @RequestParam(value="searchType", defaultValue = "") String searchType,
                               @RequestParam(value="search", defaultValue = "") String search){
        Map<String,Object> map = new HashMap<>();
        map.put("configCode",configCode);
        map.put("files",medicineService.getFilesAll());
        map.put("medicines",medicineService.getMedList(page,searchType,search));
        map.put("page",medicineService.PageInfo(page, searchType, search));
        map.put("total",medicineMapper.getMedCount(medicineService.getMedSearch(searchType,search)));
        return map;
    }

    @GetMapping("/boardView")
    public String getBoardView(@RequestParam String configCode,
                               @RequestParam int id, Model model) {

        //게시물
        model.addAttribute("board",boardService.getView(configCode,id));
        //파일
        model.addAttribute("files",boardMapper.getFile(configCode,id));
        //조회수증가
        model.addAttribute("configCode",configCode);
        boardMapper.updateVisit(configCode,id);
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(Model model,
                                @RequestParam String configCode){
        boolean isAttachFile;
        if(configCode.equals("QnA") || configCode.equals("List")){
            isAttachFile = true;
        }else{
            isAttachFile = false;
        }
        model.addAttribute("isAttachFile",isAttachFile);
        model.addAttribute("configCode",configCode);
        return "board/boardWrite";
    }

    ////////////////////글등록할떄
    @PostMapping("/boardWrite")
    @ResponseBody
    public Map<String, Object> setBoardWrite(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                             @RequestParam String configCode,
                                             @ModelAttribute BoardDto boardDto) throws IOException {

        boardDto.setBoardType(2);
        Map<String,Object> map = new HashMap<>();

        int grp = boardService.getGrpMaxCnt(configCode);
        boardDto.setGrp(grp);
        boardDto.setSeq(boardDto.getSeq() + 1);
        boardDto.setDepth(boardDto.getDepth() + 1);

        //파일 저장
        if (files != null){
            boardDto.setIsFiles("Y");
            boardService.setBoard(configCode,boardDto);

            int fileID = boardDto.getId();
            boardService.setFiles(configCode,files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "id") 사용
        }else {
            boardDto.setIsFiles("N");
            boardService.setBoard(configCode,boardDto);
        }
        map.put("msg","success");
        map.put("configCode",configCode);
        return map;
    }

    @GetMapping("/boardDelete")
    public String setDelete(@RequestParam String configCode,
                            @RequestParam int id) {

        BoardDto boardDto = boardService.getView(configCode,id);
        boardService.setDelete(configCode,boardDto);
        return "redirect:/board/board?configCode="+configCode;
    }

    @GetMapping("/boardUpdate")
    public String getUpdate(@RequestParam String configCode,
                            @RequestParam int id, Model model) {

        BoardDto bd = boardService.getView(configCode,id);
        model.addAttribute("modify",bd);
        model.addAttribute("configCode",configCode);
        return "board/boardUpdate";
    }

    //수정등록하기
    @PostMapping("/boardUpdate")
    @ResponseBody
    public Map<String, Object> setUpdate(@RequestParam String configCode,
                                         @RequestParam(name="files",required = false)List<MultipartFile> files,
                                         @ModelAttribute BoardDto boardDto) throws IOException {

        BoardDto bd = boardService.getView(configCode,boardDto.getId());
        if (files != null){
            //새로운 파일이 있다면
            boardDto.setIsFiles("Y");
            boardService.setUpdate(configCode,boardDto);

            int fileID = boardDto.getId();
            boardService.setFiles(configCode,files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "id") 사용
        }else if (bd.getIsFiles().equals("N")){
            boardDto.setIsFiles("N");
            boardService.setUpdate(configCode,boardDto);
        }else {
            boardDto.setIsFiles("Y");
            boardService.setUpdate(configCode,boardDto);
        }

        Map<String,Object> map = new HashMap<>();

        map.put("msg","success");
        map.put("configCode",configCode);
        return map;
    }

    @GetMapping("/boardReply")
    public String getReply(@RequestParam String configCode,
                           @RequestParam int id, Model model){

        BoardDto boardDto = boardService.getView(configCode,id);
        boardDto.setrId(id);
        model.addAttribute("reply", boardDto);
        model.addAttribute("configCode",configCode);
        return "board/boardReply";
    }

    //ResponseBody때문이네
    @PostMapping("/boardReply")
    @ResponseBody
    public Map<String, Object> setReply(@RequestParam String configCode,
                                        @RequestParam(name="files",required = false)List<MultipartFile> files,
                                        @RequestParam int boardId,
                                        @ModelAttribute BoardDto boardDto) throws IOException {

        BoardDto parentBd = boardService.getView(configCode,boardId);
        parentBd.setConfigCode(configCode);

        boardMapper.setReplyUpdate(parentBd);
        boardDto.setGrp(parentBd.getGrp());
        boardDto.setSeq(parentBd.getSeq()+1);
        boardDto.setDepth(parentBd.getDepth()+1);
        boardDto.setBoardType(2);
        boardDto.setWriter("관리자");

        if (files != null){
            boardDto.setIsFiles("Y");
            boardService.setBoard(configCode,boardDto);
            int fileID = boardDto.getId();
            boardService.setFiles(configCode,files,fileID);
            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "id") 사용
        }else {
            boardDto.setIsFiles("N");
            boardService.setBoard(configCode,boardDto);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg","success");
        map.put("configCode",configCode);

        return map;
    }

    @GetMapping("/boardSearchMed")
    public String boardSearchMed( Model model,
                                  @RequestParam(value="page", defaultValue = "1") int page,
                                  @RequestParam(value="searchType", defaultValue = "") String searchType,
                                  @RequestParam(value="search", defaultValue = "") String search){

        model.addAttribute("files",medicineService.getFilesAll());
        model.addAttribute("medicines",medicineService.getMedList(page,searchType,search));
        model.addAttribute("page",medicineService.PageInfo(page, searchType, search));
        model.addAttribute("total",medicineMapper.getMedCount(medicineService.getMedSearch(searchType,search)));
        model.addAttribute("configCode","List");

        return "board/boardList";
    }
}
