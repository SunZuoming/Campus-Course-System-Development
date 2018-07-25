package com.courseplatform.po;

public class SharedfileexamineTable {
    private String examineno;

    private String sharedfileno;

    private String uploader;

    private String examiner;

    private String examineresult;

    private String examinefailreason;

    public String getExamineno() {
        return examineno;
    }

    public void setExamineno(String examineno) {
        this.examineno = examineno == null ? null : examineno.trim();
    }

    public String getSharedfileno() {
        return sharedfileno;
    }

    public void setSharedfileno(String sharedfileno) {
        this.sharedfileno = sharedfileno == null ? null : sharedfileno.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner == null ? null : examiner.trim();
    }

    public String getExamineresult() {
        return examineresult;
    }

    public void setExamineresult(String examineresult) {
        this.examineresult = examineresult == null ? null : examineresult.trim();
    }

    public String getExaminefailreason() {
        return examinefailreason;
    }

    public void setExaminefailreason(String examinefailreason) {
        this.examinefailreason = examinefailreason == null ? null : examinefailreason.trim();
    }
}