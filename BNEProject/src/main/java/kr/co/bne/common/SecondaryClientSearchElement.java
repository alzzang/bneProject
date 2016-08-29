package kr.co.bne.common;

import kr.co.bne.dto.SecondaryClientDTO;

public class SecondaryClientSearchElement extends SecondaryClientDTO {

	private int idx;
	private String department_name;
	public SecondaryClientSearchElement() {
		super();
	}
	public SecondaryClientSearchElement(int sec_client_id, String address, String sec_client_name, int client_id,
			String telephone, int daily_report_id, String client_name, String representative) {
		super(sec_client_id, address, sec_client_name, client_id, telephone, daily_report_id, client_name, representative);
	}
	public SecondaryClientSearchElement(int idx, String department_name) {
		super();
		this.idx = idx;
		this.department_name = department_name;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		return "SecondaryClientSearchElement [idx=" + idx + ", department_name=" + department_name + ", getIdx()="
				+ getIdx() + ", getDepartment_name()=" + getDepartment_name() + ", getSec_client_id()="
				+ getSec_client_id() + ", getAddress()=" + getAddress() + ", getSec_client_name()="
				+ getSec_client_name() + ", getClient_id()=" + getClient_id() + ", getTelephone()=" + getTelephone()
				+ ", getDaily_report_id()=" + getDaily_report_id() + ", getClient_name()=" + getClient_name()
				+ ", getRepresentative()=" + getRepresentative() + ", toString()=" + super.toString() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
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
		SecondaryClientSearchElement other = (SecondaryClientSearchElement) obj;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (idx != other.idx)
			return false;
		return true;
	}
	
	
	
	
}
