package com.login.model.pojo;

public class AcknolegmentPojo {
	private String errorMessage;
	private Boolean status;
	
	public AcknolegmentPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AcknolegmentPojo(String errorMessage, Boolean status) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AcknolegmentPojo other = (AcknolegmentPojo) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AcknolegmentPojo [errorMessage=" + errorMessage + ", status=" + status + "]";
	}
	
}
