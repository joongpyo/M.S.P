package com.example.md_exam.service;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.mapper.BoardQnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class BoardQnaService {
    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardQnaMapper boardQnaMapper;

    public void setBoard(QnaDto qnADto) {
        boardQnaMapper.setBoard(qnADto);
    }
    public List<QnaDto> getQnaList() {
        return boardQnaMapper.getQnaList();
    }
    public QnaDto getQnaView(int qnaId) {
        return boardQnaMapper.getQnaView(qnaId);
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

    public void setDelete(int qnaId) {
        QnaDto qd = boardQnaMapper.getQnaView(qnaId);

        //게시판 db삭제
        boardQnaMapper.setDelete(qnaId);
        System.out.println(qnaId +"번 게시물을 삭제했습니다");

        //파일 db삭제
        if(qd.getIsFiles().equals("Y")){
            List<FileDto> files = boardQnaMapper.getFile(qnaId);

            for (FileDto fd : files){
                File file = new File(fd.getSavedPathName() + "/" + fd.getSavedFileName());
                file.delete();
            }
            boardQnaMapper.setFilesDelete(qnaId);
            System.out.println(qnaId +"번 게시물 파일을 삭제했습니다..");
        }

        //comment db삭제

        //reply db삭제    grp가 게시물과 매칭
    }

    public void setUpdate(QnaDto qnaDto){
        boardQnaMapper.setUpdate(qnaDto);
    }
}
