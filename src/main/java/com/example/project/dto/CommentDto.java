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

    //231223 jang
    private int bIdFk;
    private String configCode;

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

    public int getbIdFk() {
        return bIdFk;
    }

    public void setbIdFk(int bIdFk) {
        this.bIdFk = bIdFk;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "cId=" + cId +
                ", cSubject='" + cSubject + '\'' +
                ", cWriter='" + cWriter + '\'' +
                ", cComment='" + cComment + '\'' +
                ", cRegdate=" + cRegdate +
                ", bIdFk=" + bIdFk +
                ", configCode='" + configCode + '\'' +
                '}';
    }

}

