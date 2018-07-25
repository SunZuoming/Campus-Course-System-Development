package com.courseplatform.po;

public class ArticletypeTable {
    private String articletypeid;

    private String articletypename;

    private String articletypefather;

    public String getArticletypeid() {
        return articletypeid;
    }

    public void setArticletypeid(String articletypeid) {
        this.articletypeid = articletypeid == null ? null : articletypeid.trim();
    }

    public String getArticletypename() {
        return articletypename;
    }

    public void setArticletypename(String articletypename) {
        this.articletypename = articletypename == null ? null : articletypename.trim();
    }

    public String getArticletypefather() {
        return articletypefather;
    }

    public void setArticletypefather(String articletypefather) {
        this.articletypefather = articletypefather == null ? null : articletypefather.trim();
    }
}