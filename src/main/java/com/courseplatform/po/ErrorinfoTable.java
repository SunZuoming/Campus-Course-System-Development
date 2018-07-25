package com.courseplatform.po;

public class ErrorinfoTable {
    private String errorinfono;

    private String erronmessage;

    private String errorfunctionno;

    private String errorname;

    public String getErrorinfono() {
        return errorinfono;
    }

    public void setErrorinfono(String errorinfono) {
        this.errorinfono = errorinfono == null ? null : errorinfono.trim();
    }

    public String getErronmessage() {
        return erronmessage;
    }

    public void setErronmessage(String erronmessage) {
        this.erronmessage = erronmessage == null ? null : erronmessage.trim();
    }

    public String getErrorfunctionno() {
        return errorfunctionno;
    }

    public void setErrorfunctionno(String errorfunctionno) {
        this.errorfunctionno = errorfunctionno == null ? null : errorfunctionno.trim();
    }

    public String getErrorname() {
        return errorname;
    }

    public void setErrorname(String errorname) {
        this.errorname = errorname == null ? null : errorname.trim();
    }
}