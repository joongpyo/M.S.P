package com.example.project.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentDto {
    private int cId;
    private String cSubject;
    private String cWriter;
    private String cComment;

    private LocalDateTime cRegdate;
    private int bId;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcSubject() {
        return cSubject;
    }

    public void setcSubject(String cSubject) {
        this.cSubject = cSubject;
    }

    public String getcWriter() {
        return cWriter;
    }

    public void setcWriter(String cWriter) {
        this.cWriter = cWriter;
    }

    public String getcComment() {
        return cComment;
    }

    public void setcComment(String cComment) {
        this.cComment = cComment;
    }

    public LocalDateTime getcRegdate() {
        return cRegdate;
    }

    public void setcRegdate(LocalDateTime cRegdate) {
        this.cRegdate = cRegdate;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "cId=" + cId +
                ", cSubject='" + cSubject + '\'' +
                ", cWriter='" + cWriter + '\'' +
                ", cComment='" + cComment + '\'' +
                ", cRegdate=" + cRegdate +
                ", bId=" + bId +
                '}';
    }
}
