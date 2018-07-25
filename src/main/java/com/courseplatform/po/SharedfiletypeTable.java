package com.courseplatform.po;

public class SharedfiletypeTable {
    private String sharedfiletypeid;

    private String sharedfiletypename;

    private String sharedfiletypefather;

    public String getSharedfiletypeid() {
        return sharedfiletypeid;
    }

    public void setSharedfiletypeid(String sharedfiletypeid) {
        this.sharedfiletypeid = sharedfiletypeid == null ? null : sharedfiletypeid.trim();
    }

    public String getSharedfiletypename() {
        return sharedfiletypename;
    }

    public void setSharedfiletypename(String sharedfiletypename) {
        this.sharedfiletypename = sharedfiletypename == null ? null : sharedfiletypename.trim();
    }

    public String getSharedfiletypefather() {
        return sharedfiletypefather;
    }

    public void setSharedfiletypefather(String sharedfiletypefather) {
        this.sharedfiletypefather = sharedfiletypefather == null ? null : sharedfiletypefather.trim();
    }
}