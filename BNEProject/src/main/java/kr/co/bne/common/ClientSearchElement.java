package kr.co.bne.common;

import kr.co.bne.dto.ClientDTO;

public class ClientSearchElement extends ClientDTO{
	private int idx;

	public ClientSearchElement() {
		super();
	}

	public ClientSearchElement(int client_id, String client_name, String representative, int department_id,
			String address, String telephone, String department_name) {
		super(client_id, client_name, representative, department_id, address, telephone, department_name);
	}

	public ClientSearchElement(int idx) {
		super();
		this.idx = idx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "ClientSearchElement [idx=" + idx + ", getIdx()=" + getIdx() + ", getClient_id()=" + getClient_id()
				+ ", getClient_name()=" + getClient_name() + ", getRepresentative()=" + getRepresentative()
				+ ", getDepartment_id()=" + getDepartment_id() + ", getAddress()=" + getAddress() + ", getTelephone()="
				+ getTelephone() + ", getDepartment_name()=" + getDepartment_name() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idx;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientSearchElement other = (ClientSearchElement) obj;
		if (idx != other.idx)
			return false;
		return true;
	}
	
	

}
