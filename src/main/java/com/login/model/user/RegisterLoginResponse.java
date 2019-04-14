package com.login.model.user;

import org.apache.commons.lang3.Validate;

import com.login.model.pojo.AcknolegmentPojo;
import com.login.model.pojo.DataPojo;

public class RegisterLoginResponse {
	private AcknolegmentPojo ack;
	private DataPojo data;
	
	public RegisterLoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterLoginResponse(AcknolegmentPojo ack, DataPojo data) {
		super();
		Validate.notNull(ack, "ack can't be null");
		this.ack = ack;
		this.data = data;
	}

	public AcknolegmentPojo getAck() {
		return ack;
	}

	public void setAck(AcknolegmentPojo ack) {
		this.ack = ack;
	}

	public DataPojo getData() {
		return data;
	}

	public void setData(DataPojo data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ack == null) ? 0 : ack.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		RegisterLoginResponse other = (RegisterLoginResponse) obj;
		if (ack == null) {
			if (other.ack != null)
				return false;
		} else if (!ack.equals(other.ack))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisterLoginResponse [ack=" + ack + ", data=" + data + "]";
	}
}
