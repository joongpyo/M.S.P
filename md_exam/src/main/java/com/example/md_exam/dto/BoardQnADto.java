package com.example.md_exam.dto;

import java.time.LocalDate;

public class BoardQnADto {
    private int qId;
    private String qSubject;
    private String qContent;
    private int visit;
    private LocalDate qRegdate;
    private String qOrgName;
    private String qSavedFileName;
    private String qSavedFilePathName;
    private Long qSavedFileSize;
    private String qFolderName;
    private String qExt;
    private int qGrp;
    private int qSeq;
    private int qDepth;
    private int qMedicineIdFk;

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getqSubject() {
        return qSubject;
    }

    public void setqSubject(String qSubject) {
        this.qSubject = qSubject;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public LocalDate getqRegdate() {
        return qRegdate;
    }

    public void setqRegdate(LocalDate qRegdate) {
        this.qRegdate = qRegdate;
    }

    public String getqOrgName() {
        return qOrgName;
    }

    public void setqOrgName(String qOrgName) {
        this.qOrgName = qOrgName;
    }

    public String getqSavedFileName() {
        return qSavedFileName;
    }

    public void setqSavedFileName(String qSavedFileName) {
        this.qSavedFileName = qSavedFileName;
    }

    public String getqSavedFilePathName() {
        return qSavedFilePathName;
    }

    public void setqSavedFilePathName(String qSavedFilePathName) {
        this.qSavedFilePathName = qSavedFilePathName;
    }

    public Long getqSavedFileSize() {
        return qSavedFileSize;
    }

    public void setqSavedFileSize(Long qSavedFileSize) {
        this.qSavedFileSize = qSavedFileSize;
    }

    public String getqFolderName() {
        return qFolderName;
    }

    public void setqFolderName(String qFolderName) {
        this.qFolderName = qFolderName;
    }

    public String getqExt() {
        return qExt;
    }

    public void setqExt(String qExt) {
        this.qExt = qExt;
    }

    public int getqGrp() {
        return qGrp;
    }

    public void setqGrp(int qGrp) {
        this.qGrp = qGrp;
    }

    public int getqSeq() {
        return qSeq;
    }

    public void setqSeq(int qSeq) {
        this.qSeq = qSeq;
    }

    public int getqDepth() {
        return qDepth;
    }

    public void setqDepth(int qDepth) {
        this.qDepth = qDepth;
    }

    public int getqMedicineIdFk() {
        return qMedicineIdFk;
    }

    public void setqMedicineIdFk(int qMedicineIdFk) {
        this.qMedicineIdFk = qMedicineIdFk;
    }

    @Override
    public String toString() {
        return "BoradQnADto{" +
                "qId=" + qId +
                ", qSubject='" + qSubject + '\'' +
                ", qContent='" + qContent + '\'' +
                ", visit=" + visit +
                ", qRegdate=" + qRegdate +
                ", qOrgName='" + qOrgName + '\'' +
                ", qSavedFileName='" + qSavedFileName + '\'' +
                ", qSavedFilePathName='" + qSavedFilePathName + '\'' +
                ", qSavedFileSize=" + qSavedFileSize +
                ", qFolderName='" + qFolderName + '\'' +
                ", qExt='" + qExt + '\'' +
                ", qGrp=" + qGrp +
                ", qSeq=" + qSeq +
                ", qDepth=" + qDepth +
                ", qMedicineIdFk=" + qMedicineIdFk +
                '}';
    }

}
