package com.courseplatform.po;

public class ParameterTable {
    private String parameterno;

    private String parametername;

    private String parametervalue;

    private String parameternote;

    public String getParameterno() {
        return parameterno;
    }

    public void setParameterno(String parameterno) {
        this.parameterno = parameterno == null ? null : parameterno.trim();
    }

    public String getParametername() {
        return parametername;
    }

    public void setParametername(String parametername) {
        this.parametername = parametername == null ? null : parametername.trim();
    }

    public String getParametervalue() {
        return parametervalue;
    }

    public void setParametervalue(String parametervalue) {
        this.parametervalue = parametervalue == null ? null : parametervalue.trim();
    }

    public String getParameternote() {
        return parameternote;
    }

    public void setParameternote(String parameternote) {
        this.parameternote = parameternote == null ? null : parameternote.trim();
    }
}