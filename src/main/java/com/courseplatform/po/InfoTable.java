package com.courseplatform.po;

public class InfoTable {
	private String infono;
	
	private String info;
	
	private String infotype;
	
	private String infotime;
	
	private String infouserno;
	
	private String inforeadsign;

	public String getInfono() {
		return infono;
	}

	public String getInfotime() {
		return infotime;
	}

	public void setInfotime(String infotime) {
		this.infotime = infotime;
	}

	public String getInfouserno() {
		return infouserno;
	}

	public void setInfouserno(String infouserno) {
		this.infouserno = infouserno;
	}

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public void setInfono(String infono) {
		this.infono = infono;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInforeadsign() {
		return inforeadsign;
	}

	public void setInforeadsign(String inforeadsign) {
		this.inforeadsign = inforeadsign;
	}
}
