package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class SharedFileDTO {

	private String sharedfileno;

    private User uploader;

    private String uploadtime;

    private String sharedfileurl;

    private String sharedfiletypes;

    private String sharedfilepassflag;

    private Integer sharedfilegoognum;

    private Integer sharedfilebadnum;

    private Integer sharedfilecollectnum;

    private Integer sharedfilereportnum;

    private Integer sharedfiledownloadnum;

    private String sharedfilename;

    private Float sharedfilegoodrate;

	public String getSharedfileno() {
		return sharedfileno;
	}

	public void setSharedfileno(String sharedfileno) {
		this.sharedfileno = sharedfileno;
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getSharedfileurl() {
		return sharedfileurl;
	}

	public void setSharedfileurl(String sharedfileurl) {
		this.sharedfileurl = sharedfileurl;
	}

	public String getSharedfiletypes() {
		return sharedfiletypes;
	}

	public void setSharedfiletypes(String sharedfiletypes) {
		this.sharedfiletypes = sharedfiletypes;
	}

	public String getSharedfilepassflag() {
		return sharedfilepassflag;
	}

	public void setSharedfilepassflag(String sharedfilepassflag) {
		this.sharedfilepassflag = sharedfilepassflag;
	}

	public Integer getSharedfilegoognum() {
		return sharedfilegoognum;
	}

	public void setSharedfilegoognum(Integer sharedfilegoognum) {
		this.sharedfilegoognum = sharedfilegoognum;
	}

	public Integer getSharedfilebadnum() {
		return sharedfilebadnum;
	}

	public void setSharedfilebadnum(Integer sharedfilebadnum) {
		this.sharedfilebadnum = sharedfilebadnum;
	}

	public Integer getSharedfilecollectnum() {
		return sharedfilecollectnum;
	}

	public void setSharedfilecollectnum(Integer sharedfilecollectnum) {
		this.sharedfilecollectnum = sharedfilecollectnum;
	}

	public Integer getSharedfilereportnum() {
		return sharedfilereportnum;
	}

	public void setSharedfilereportnum(Integer sharedfilereportnum) {
		this.sharedfilereportnum = sharedfilereportnum;
	}

	public Integer getSharedfiledownloadnum() {
		return sharedfiledownloadnum;
	}

	public void setSharedfiledownloadnum(Integer sharedfiledownloadnum) {
		this.sharedfiledownloadnum = sharedfiledownloadnum;
	}

	public String getSharedfilename() {
		return sharedfilename;
	}

	public void setSharedfilename(String sharedfilename) {
		this.sharedfilename = sharedfilename;
	}

	public Float getSharedfilegoodrate() {
		return sharedfilegoodrate;
	}

	public void setSharedfilegoodrate(Float sharedfilegoodrate) {
		this.sharedfilegoodrate = sharedfilegoodrate;
	}
}
