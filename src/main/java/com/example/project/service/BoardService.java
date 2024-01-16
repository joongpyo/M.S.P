package com.example.project.service;

import com.example.project.dto.BoardDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import com.example.project.dto.PageDto;
import com.example.project.mapper.BoardMapper;
import com.example.project.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BoardService {
    @Value("${fileDir}")
    String fileDir;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    CommentMapper commentMapper;

    public List<BoardDto> getBoard(String configCode, int page, String searchType, String search) {
        PageDto pd = PageInfo(configCode,page, searchType, search);
        String searchQuery = getSearch(searchType,search);

        Map<String,Object> map = new HashMap<>();
        map.put("configCode",configCode);
        map.put("startNum", pd.getStartNum());
        map.put("offset", pd.getPageCount());
        map.put("searchQuery",searchQuery);

        return boardMapper.getBoard(map);
    }
    public int getBoardCount(String configCode,String searchType,String search){
        String searchQuery = getSearch(searchType,search);
        return boardMapper.getBoardCount(configCode,searchQuery);
    }
    //
    public PageDto PageInfo(String configCode,int page, String searchType, String search) {
        PageDto pageDto = new PageDto();

        String searchQuery = getSearch(searchType,search);
        int totalCount = boardMapper.getBoardCount(configCode,searchQuery);

        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage =  ((int) (Math.ceil((double) page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if( endPage > totalPage ) {
            endPage = totalPage;
        }

        pageDto.setStartNum( (page - 1) * pageDto.getPageCount()  );
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        return pageDto;
    }
    public BoardDto getView(String configCode, int id) {
        return boardMapper.getView(configCode,id);
    }
    public int getGrpMaxCnt(String configCode){
        return boardMapper.getGrpMaxCnt(configCode);
    }
    public void setBoard(String configCode,BoardDto boardDto) {

        boardDto.setConfigCode(configCode);
        boardMapper.setBoard(boardDto);
    }
    public void setFiles(String configCode,List<MultipartFile> files, int fileID) throws IOException {

        if (files != null) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            for (MultipartFile mf : files) {
                FileDto fileDto = new FileDto();

                String savedPathName = fileDir + folderName;
                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;
                Long savedFileSize = mf.getSize();

                mf.transferTo(new File(savedPathName + "/" + savedFileName));

                fileDto.setId(fileID);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathName);
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);
                fileDto.setSavedFileSize(savedFileSize);
                fileDto.setConfigCode(configCode);
                boardMapper.setFiles(fileDto);
            }
        }
    }

    public void setDelete(String configCode,BoardDto boardDto) {
        boardMapper.setDelete(configCode,boardDto.getId());
    }

    //////////////////////////////////메서드

    public String getSearch(String searchType, String search){
        String searchQuery = "";
        if(searchType.equals("writer")){
            searchQuery = " WHERE writer = '"+search+"'";
        }else if (searchType.equals("content")){
            searchQuery = " WHERE content LIKE '%"+search+"%'";
        }else if (searchType.equals("subject")){
            searchQuery = " WHERE subject LIKE '%"+search+"%'";
        }else{
            searchQuery = "";
        }
        return searchQuery;
    }

    public void setUpdate(String configCode,BoardDto boardDto){
        boardDto.setConfigCode(configCode);
        boardMapper.setUpdate(boardDto);
    }

    public List<MedicineDto> getMed(){
        return boardMapper.getMed();
    }
    public List<FileDto> getMedFiles(){
        return boardMapper.getMedFiles();
    }
    public void setReply(String configCode,BoardDto boardDto) {

        boardDto.setConfigCode(configCode);
        boardMapper.setReply(boardDto);
    }



}
