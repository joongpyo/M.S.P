package com.example.md_exam.dto;

import java.time.LocalDate;

public class QnaDto {
    private int qnaId;
    private String qnaSubject;
    private String qnaWriter;
    private String qnaContent;
    private int qnaVisit;
    private LocalDate qnaReg;
    private int grp;
    private int seq;
    private int depth;
    private int uIdFk;
    private String isFiles;

    public int getQnaId() {
        return qnaId;
    }

    public void setQnaId(int qnaId) {
        this.qnaId = qnaId;
    }

    public String getQnaSubject() {
        return qnaSubject;
    }

    public void setQnaSubject(String qnaSubject) {
        this.qnaSubject = qnaSubject;
    }

    public String getQnaWriter() {
        return qnaWriter;
    }

    public void setQnaWriter(String qnaWriter) {
        this.qnaWriter = qnaWriter;
    }

    public String getQnaContent() {
        return qnaContent;
    }

    public void setQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }

    public int getQnaVisit() {
        return qnaVisit;
    }

    public void setQnaVisit(int qnaVisit) {
        this.qnaVisit = qnaVisit;
    }

    public LocalDate getQnaReg() {
        return qnaReg;
    }

    public void setQnaReg(LocalDate qnaReg) {
        this.qnaReg = qnaReg;
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

    public int getuIdFk() {
        return uIdFk;
    }

    public void setuIdFk(int uIdFk) {
        this.uIdFk = uIdFk;
    }

    public String getIsFiles() {
        return isFiles;
    }

    public void setIsFiles(String isFiles) {
        this.isFiles = isFiles;
    }

    @Override
    public String toString() {
        return "QnADto{" +
                "qnaId=" + qnaId +
                ", qnaSubject='" + qnaSubject + '\'' +
                ", qnaWriter='" + qnaWriter + '\'' +
                ", qnaContent='" + qnaContent + '\'' +
                ", qnaVisit=" + qnaVisit +
                ", qnaReg=" + qnaReg +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                ", uIdFk=" + uIdFk +
                ", isFiles='" + isFiles + '\'' +
                '}';
    }
}
