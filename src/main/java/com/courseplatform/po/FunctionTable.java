package com.courseplatform.po;

public class FunctionTable {
    private String functionno;

    private String functionname;

    private String functionurl;

    private String functionfather;

    private String functionnote;

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno == null ? null : functionno.trim();
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname == null ? null : functionname.trim();
    }

    public String getFunctionurl() {
        return functionurl;
    }

    public void setFunctionurl(String functionurl) {
        this.functionurl = functionurl == null ? null : functionurl.trim();
    }

    public String getFunctionfather() {
        return functionfather;
    }

    public void setFunctionfather(String functionfather) {
        this.functionfather = functionfather == null ? null : functionfather.trim();
    }

    public String getFunctionnote() {
        return functionnote;
    }

    public void setFunctionnote(String functionnote) {
        this.functionnote = functionnote == null ? null : functionnote.trim();
    }
}