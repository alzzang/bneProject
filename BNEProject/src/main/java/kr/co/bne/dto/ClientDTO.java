package kr.co.bne.dto;

public class ClientDTO {
	private int client_id;
	private String client_name;
	private String representative;
	public ClientDTO() {
		super();
		// TODO Auto-generated conxstructor stub
	}
	public ClientDTO(int client_id, String client_name, String representative) {
		super();
		this.client_id = client_id;
		this.client_name = client_name;
		this.representative = representative;
	}
	@Override
	public String toString() {
		return "ClientDTO [client_id=" + client_id + ", client_name=" + client_name + ", representative="
				+ representative + "]";
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + client_id;
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + ((representative == null) ? 0 : representative.hashCode());
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
		if (client_id != other.client_id)
			return false;
		if (client_name == null) {
			if (other.client_name != null)
				return false;
		} else if (!client_name.equals(other.client_name))
			return false;
		if (representative == null) {
			if (other.representative != null)
				return false;
		} else if (!representative.equals(other.representative))
			return false;
		return true;
	}
	
	
}
