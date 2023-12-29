package com.example.project.dto;

public class DiseaseDto {
    private int disId;
    private String disName;
    private String disSym;
    private String disReg;

    public int getDisId() {
        return disId;
    }

    public void setDisId(int disId) {
        this.disId = disId;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisSym() {
        return disSym;
    }

    public void setDisSym(String disSym) {
        this.disSym = disSym;
    }

    public String getDisReg() {
        return disReg;
    }

    public void setDisReg(String disReg) {
        this.disReg = disReg;
    }

    @Override
    public String toString() {
        return "DiseaseDto{" +
                "disId=" + disId +
                ", disName='" + disName + '\'' +
                ", disSym='" + disSym + '\'' +
                ", disReg='" + disReg + '\'' +
                '}';
    }
}
