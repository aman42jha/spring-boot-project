package com.login.model.pojo;

public class DataPojo {
	private String token;
	private UserBriefDetailsPojo user;
	
	public DataPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DataPojo(String token, UserBriefDetailsPojo user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public UserBriefDetailsPojo getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		DataPojo other = (DataPojo) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataPojo [token=" + token + ", user=" + user + "]";
	}
}
