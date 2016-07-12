package kr.co.bne.dto;

public class CounsellingDetailDTO {
	private int counsel_id;
	private String department_name;
	private String employee_name;
	private String reg_date;
	private String title;
	private int client_id;
	private String client_name;
	private String representative;
	private int sec_client_id;
	private String sec_client_name;
	private String address;
	private String content;
	public int getCounsel_id() {
		return counsel_id;
	}
	public void setCounsel_id(int counsel_id) {
		this.counsel_id = counsel_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getSec_client_name() {
		return sec_client_name;
	}
	public void setSec_client_name(String sec_client_name) {
		this.sec_client_name = sec_client_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CounsellingDetailDTO [counsel_id=" + counsel_id + ", department_name=" + department_name
				+ ", employee_name=" + employee_name + ", reg_date=" + reg_date + ", title=" + title + ", client_id="
				+ client_id + ", client_name=" + client_name + ", representative=" + representative + ", sec_client_id="
				+ sec_client_id + ", sec_client_name=" + sec_client_name + ", address=" + address + ", content="
				+ content + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + client_id;
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + counsel_id;
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + ((representative == null) ? 0 : representative.hashCode());
		result = prime * result + ((sec_client_name == null) ? 0 : sec_client_name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		CounsellingDetailDTO other = (CounsellingDetailDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (client_id != other.client_id)
			return false;
		if (client_name == null) {
			if (other.client_name != null)
				return false;
		} else if (!client_name.equals(other.client_name))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (counsel_id != other.counsel_id)
			return false;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (representative == null) {
			if (other.representative != null)
				return false;
		} else if (!representative.equals(other.representative))
			return false;
		if (sec_client_name == null) {
			if (other.sec_client_name != null)
				return false;
		} else if (!sec_client_name.equals(other.sec_client_name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	public CounsellingDetailDTO(){
		
	}
	public CounsellingDetailDTO(int counsel_id, String department_name, String employee_name, String reg_date,
			String title, int client_id, String client_name, String representative, String sec_client_name,
			String address, String content) {
		super();
		this.counsel_id = counsel_id;
		this.department_name = department_name;
		this.employee_name = employee_name;
		this.reg_date = reg_date;
		this.title = title;
		this.client_id = client_id;
		this.client_name = client_name;
		this.representative = representative;
		this.sec_client_name = sec_client_name;
		this.address = address;
		this.content = content;
	}
	public int getSec_client_id() {
		return sec_client_id;
	}
	public void setSec_client_id(int sec_client_id) {
		this.sec_client_id = sec_client_id;
	}
	
}
