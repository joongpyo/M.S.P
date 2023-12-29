package com.example.dto;

import java.time.LocalDate;

public class AdminBoardDto {
    private String configCode;
    private int id;
    private String subject;
    private String writer;
    private String content;
    private int visit;
    private LocalDate reg;
    private int grp;
    private int seq;
    private int depth;
    private String isFiles;
    private int commentCount;
    private int uIdFk;
    private int boardType;

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public LocalDate getReg() {
        return reg;
    }

    public void setReg(LocalDate reg) {
        this.reg = reg;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getIsFiles() {
        return isFiles;
    }

    public void setIsFiles(String isFiles) {
        this.isFiles = isFiles;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getuIdFk() {
        return uIdFk;
    }

    public void setuIdFk(int uIdFk) {
        this.uIdFk = uIdFk;
    }

    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    @Override
    public String toString() {
        return "AdminBoardDto{" +
                "configCode='" + configCode + '\'' +
                ", id=" + id +
                ", subject='" + subject + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", visit=" + visit +
                ", reg=" + reg +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                ", isFiles='" + isFiles + '\'' +
                ", commentCount=" + commentCount +
                ", uIdFk=" + uIdFk +
                ", boardType=" + boardType +
                '}';
    }
}
