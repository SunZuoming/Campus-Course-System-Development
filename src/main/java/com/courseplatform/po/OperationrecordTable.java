package com.courseplatform.po;

public class OperationrecordTable {
    private String operationrecordno;

    private String functionno;

    private String operationtime;

    private String operationresult;

    private String errorname;

    public String getOperationrecordno() {
        return operationrecordno;
    }

    public void setOperationrecordno(String operationrecordno) {
        this.operationrecordno = operationrecordno == null ? null : operationrecordno.trim();
    }

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno == null ? null : functionno.trim();
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime == null ? null : operationtime.trim();
    }

    public String getOperationresult() {
        return operationresult;
    }

    public void setOperationresult(String operationresult) {
        this.operationresult = operationresult == null ? null : operationresult.trim();
    }

    public String getErrorname() {
        return errorname;
    }

    public void setErrorname(String errorname) {
        this.errorname = errorname == null ? null : errorname.trim();
    }
}