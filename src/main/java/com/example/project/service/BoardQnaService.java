package com.example.project.service;

import com.example.project.dto.FileDto;
import com.example.project.dto.PageDto;
import com.example.project.dto.QnaDto;
import com.example.project.mapper.BoardQnaMapper;
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
public class BoardQnaService {
    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardQnaMapper boardQnaMapper;



    //게시물 검색
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


    public PageDto PageInfo(int page, String searchType, String search) {
        PageDto pageDto = new PageDto();

        String searchQuery = getSearch(searchType,search);
        int totalCount = boardQnaMapper.getBoardCount(searchQuery);

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

    public List<QnaDto> getBoardQnA(int page, String searchType, String search) {
        PageDto pd = PageInfo(page, searchType, search);
        String searchQuery = getSearch(searchType,search);

        Map<String,Object> map = new HashMap<>();

        map.put("startNum", pd.getStartNum());
        map.put("offset", pd.getPageCount());
        map.put("searchQuery",searchQuery);

        return boardQnaMapper.getBoardQnA(map);
    }
    public void setBoard(QnaDto qnADto) {
        boardQnaMapper.setBoard(qnADto);
    }

    public QnaDto getQnaView(int id) {
        return boardQnaMapper.getQnaView(id);
    }

    int getBoardCount(String searchQuery){
        return boardQnaMapper.getBoardCount(searchQuery);
    }

    public void setFiles(List<MultipartFile> files, int fileID) throws IOException {

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
                boardQnaMapper.setFiles(fileDto);
            }
        }
    }

    public void setDelete(int id) {
        QnaDto qd = boardQnaMapper.getQnaView(id);

        //게시판 db삭제
        boardQnaMapper.setDelete(id);
        System.out.println(id +"번 게시물을 삭제했습니다");

        //파일 db삭제
        if(qd.getIsFiles().equals("Y")){
            List<FileDto> files = boardQnaMapper.getFile(id);

            for (FileDto fd : files){
                File file = new File(fd.getSavedPathName() + "/" + fd.getSavedFileName());
                file.delete();
            }
            boardQnaMapper.setFilesDelete(id);
            System.out.println(id +"번 게시물 파일을 삭제했습니다..");
        }

        //comment db삭제

        //reply db삭제    grp가 게시물과 매칭
    }

    public void setUpdate(QnaDto qnaDto){
        System.out.println(qnaDto);
        boardQnaMapper.setUpdate(qnaDto);
    }

    public int getBoardCount(String searchType,String search){
        String searchQuery = getSearch(searchType,search);
        return boardQnaMapper.getBoardCount(searchQuery);
    }

}
