package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class ProhibitDTO {

	private String prohibitLoginReason;
	private String prohibitLoginer;
	private User prohibitLogineer;
	private String prohibitLoginTime;
	private String prohibitLoginType;
	private String prohibitLoginFlag;
	private String prohibitLoginOperations;
	private String prohibitCommitFlag;
	private String prohibitCommitTime;
	private Integer prohibitLoginSurplusDays;

	public String getProhibitLoginReason() {
		return prohibitLoginReason;
	}

	public void setProhibitLoginReason(String prohibitLoginReason) {
		this.prohibitLoginReason = prohibitLoginReason;
	}

	public String getProhibitLoginer() {
		return prohibitLoginer;
	}

	public void setProhibitLoginer(String prohibitLoginer) {
		this.prohibitLoginer = prohibitLoginer;
	}

	public User getProhibitLogineer() {
		return prohibitLogineer;
	}

	public void setProhibitLogineer(User prohibitLogineer) {
		this.prohibitLogineer = prohibitLogineer;
	}

	public String getProhibitLoginTime() {
		return prohibitLoginTime;
	}

	public void setProhibitLoginTime(String prohibitLoginTime) {
		this.prohibitLoginTime = prohibitLoginTime;
	}

	public String getProhibitLoginType() {
		return prohibitLoginType;
	}

	public void setProhibitLoginType(String prohibitLoginType) {
		this.prohibitLoginType = prohibitLoginType;
	}

	public String getProhibitLoginFlag() {
		return prohibitLoginFlag;
	}

	public void setProhibitLoginFlag(String prohibitLoginFlag) {
		this.prohibitLoginFlag = prohibitLoginFlag;
	}

	public String getProhibitLoginOperations() {
		return prohibitLoginOperations;
	}

	public void setProhibitLoginOperations(String prohibitLoginOperations) {
		this.prohibitLoginOperations = prohibitLoginOperations;
	}

	public String getProhibitCommitFlag() {
		return prohibitCommitFlag;
	}

	public void setProhibitCommitFlag(String prohibitCommitFlag) {
		this.prohibitCommitFlag = prohibitCommitFlag;
	}

	public String getProhibitCommitTime() {
		return prohibitCommitTime;
	}

	public void setProhibitCommitTime(String prohibitCommitTime) {
		this.prohibitCommitTime = prohibitCommitTime;
	}

	public Integer getProhibitLoginSurplusDays() {
		return prohibitLoginSurplusDays;
	}

	public void setProhibitLoginSurplusDays(Integer prohibitLoginSurplusDays) {
		this.prohibitLoginSurplusDays = prohibitLoginSurplusDays;
	}

}
