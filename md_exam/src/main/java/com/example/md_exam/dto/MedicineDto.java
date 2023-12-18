package com.example.md_exam.dto;

public class MedicineDto {
    private int medId;
    private String medName;
    private String medDis;
    private String medEff;
    private String medType;
    private String medStore;
    private String medCom;
    private int medAge;
    private String medPregnant;
    private String medReg;
    private String medIsFiles;

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedDis() {
        return medDis;
    }

    public void setMedDis(String medDis) {
        this.medDis = medDis;
    }

    public String getMedEff() {
        return medEff;
    }

    public void setMedEff(String medEff) {
        this.medEff = medEff;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public String getMedStore() {
        return medStore;
    }

    public void setMedStore(String medStore) {
        this.medStore = medStore;
    }

    public String getMedCom() {
        return medCom;
    }

    public void setMedCom(String medCom) {
        this.medCom = medCom;
    }

    public int getMedAge() {
        return medAge;
    }

    public void setMedAge(int medAge) {
        this.medAge = medAge;
    }

    public String getMedPregnant() {
        return medPregnant;
    }

    public void setMedPregnant(String medPregnant) {
        this.medPregnant = medPregnant;
    }

    public String getMedReg() {
        return medReg;
    }

    public void setMedReg(String medReg) {
        this.medReg = medReg;
    }

    public String getMedIsFiles() {
        return medIsFiles;
    }

    public void setMedIsFiles(String medIsFiles) {
        this.medIsFiles = medIsFiles;
    }

    @Override
    public String toString() {
        return "MedicineDto{" +
                "medId=" + medId +
                ", medName='" + medName + '\'' +
                ", medDis='" + medDis + '\'' +
                ", medEff='" + medEff + '\'' +
                ", medType='" + medType + '\'' +
                ", medStore='" + medStore + '\'' +
                ", medCom='" + medCom + '\'' +
                ", medAge='" + medAge + '\'' +
                ", medPregnant='" + medPregnant + '\'' +
                ", medReg='" + medReg + '\'' +
                ", medIsFiles='" + medIsFiles + '\'' +
                '}';
    }
}
