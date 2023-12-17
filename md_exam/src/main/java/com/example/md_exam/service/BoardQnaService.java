package com.example.md_exam.service;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.mapper.BoardQnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class BoardQnaService {
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
    public void setFiles(FileDto fileDto) {
        boardQnaMapper.setFiles(fileDto);
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
}
