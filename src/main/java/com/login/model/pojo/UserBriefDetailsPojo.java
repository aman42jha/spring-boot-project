package com.login.model.pojo;

public class UserBriefDetailsPojo {
	private int id;
	private String userName;
	private String gender;
	
	public UserBriefDetailsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBriefDetailsPojo(int id, String userName, String gender) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBriefDetailsPojo other = (UserBriefDetailsPojo) obj;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBriefDetailsPojo [id=" + id + ", userName=" + userName + ", gender=" + gender + "]";
	}
	
	
}
