package com.courseplatform.po;

public class OperationtypeTable {
    private String operationtypeid;

    private String operationtypeno;

    private String operationtypename;

    public String getOperationtypeid() {
        return operationtypeid;
    }

    public void setOperationtypeid(String operationtypeid) {
        this.operationtypeid = operationtypeid == null ? null : operationtypeid.trim();
    }

    public String getOperationtypeno() {
        return operationtypeno;
    }

    public void setOperationtypeno(String operationtypeno) {
        this.operationtypeno = operationtypeno == null ? null : operationtypeno.trim();
    }

    public String getOperationtypename() {
        return operationtypename;
    }

    public void setOperationtypename(String operationtypename) {
        this.operationtypename = operationtypename == null ? null : operationtypename.trim();
    }
}