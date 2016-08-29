package kr.co.bne.dto;

public class ClientDTO {
	private int client_id;
	private String client_name;
	private String representative;
	private int department_id;
	private String address;
	private String telephone;
	
	private String department_name;

	public ClientDTO() {
		super();
	}

	public ClientDTO(int client_id, String client_name, String representative, int department_id, String address,
			String telephone, String department_name) {
		super();
		this.client_id = client_id;
		this.client_name = client_name;
		this.representative = representative;
		this.department_id = department_id;
		this.address = address;
		this.telephone = telephone;
		this.department_name = department_name;
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

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + client_id;
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + department_id;
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + ((representative == null) ? 0 : representative.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		ClientDTO other = (ClientDTO) obj;
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
		if (department_id != other.department_id)
			return false;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (representative == null) {
			if (other.representative != null)
				return false;
		} else if (!representative.equals(other.representative))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientDTO [client_id=" + client_id + ", client_name=" + client_name + ", representative="
				+ representative + ", department_id=" + department_id + ", address=" + address + ", telephone="
				+ telephone + ", department_name=" + department_name + "]";
	}

	

}
