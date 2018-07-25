package com.courseplatform.po;

public class User {
	private String userNo;
	private String roleId;
	private String userName;
	private String userPassword;
	private String loginFirstflag;
	private Float userSystemscore;
	private String userPhone;
	private Integer warningNum;
	private String userState;
	private Integer loginFailnumber;
	private Integer prohibitLoginnum;
	private Integer warningAfterprohibitnum;
	private String email;
	private String activeState;
	private String userCode;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLoginFirstflag() {
		return loginFirstflag;
	}

	public void setLoginFirstflag(String loginFirstflag) {
		this.loginFirstflag = loginFirstflag;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getWarningNum() {
		return warningNum;
	}

	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public Integer getLoginFailnumber() {
		return loginFailnumber;
	}

	public void setLoginFailnumber(Integer loginFailnumber) {
		this.loginFailnumber = loginFailnumber;
	}

	public Integer getProhibitLoginnum() {
		return prohibitLoginnum;
	}

	public void setProhibitLoginnum(Integer prohibitLoginnum) {
		this.prohibitLoginnum = prohibitLoginnum;
	}

	public Integer getWarningAfterprohibitnum() {
		return warningAfterprohibitnum;
	}

	public void setWarningAfterprohibitnum(Integer warningAfterprohibitnum) {
		this.warningAfterprohibitnum = warningAfterprohibitnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeState == null) ? 0 : activeState.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((loginFailnumber == null) ? 0 : loginFailnumber.hashCode());
		result = prime * result + ((loginFirstflag == null) ? 0 : loginFirstflag.hashCode());
		result = prime * result + ((prohibitLoginnum == null) ? 0 : prohibitLoginnum.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
		result = prime * result + ((userState == null) ? 0 : userState.hashCode());
		result = prime * result + ((userSystemscore == null) ? 0 : userSystemscore.hashCode());
		result = prime * result + ((warningAfterprohibitnum == null) ? 0 : warningAfterprohibitnum.hashCode());
		result = prime * result + ((warningNum == null) ? 0 : warningNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (activeState == null) {
			if (other.activeState != null)
				return false;
		} else if (!activeState.equals(other.activeState))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loginFailnumber == null) {
			if (other.loginFailnumber != null)
				return false;
		} else if (!loginFailnumber.equals(other.loginFailnumber))
			return false;
		if (loginFirstflag == null) {
			if (other.loginFirstflag != null)
				return false;
		} else if (!loginFirstflag.equals(other.loginFirstflag))
			return false;
		if (prohibitLoginnum == null) {
			if (other.prohibitLoginnum != null)
				return false;
		} else if (!prohibitLoginnum.equals(other.prohibitLoginnum))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userNo == null) {
			if (other.userNo != null)
				return false;
		} else if (!userNo.equals(other.userNo))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userPhone == null) {
			if (other.userPhone != null)
				return false;
		} else if (!userPhone.equals(other.userPhone))
			return false;
		if (userState == null) {
			if (other.userState != null)
				return false;
		} else if (!userState.equals(other.userState))
			return false;
		if (userSystemscore == null) {
			if (other.userSystemscore != null)
				return false;
		} else if (!userSystemscore.equals(other.userSystemscore))
			return false;
		if (warningAfterprohibitnum == null) {
			if (other.warningAfterprohibitnum != null)
				return false;
		} else if (!warningAfterprohibitnum.equals(other.warningAfterprohibitnum))
			return false;
		if (warningNum == null) {
			if (other.warningNum != null)
				return false;
		} else if (!warningNum.equals(other.warningNum))
			return false;
		return true;
	}

	public Float getUserSystemscore() {
		return userSystemscore;
	}

	public void setUserSystemscore(Float userSystemscore) {
		this.userSystemscore = userSystemscore;
	}

}
