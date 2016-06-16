package kr.co.bne.dto;

public class SecondaryClientDTO {
	private int sec_client_id;
	private String address;
	private String sec_client_name;
	private int client_id;
	public SecondaryClientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SecondaryClientDTO(int sec_client_id, String address, String sec_client_name, int client_id) {
		super();
		this.sec_client_id = sec_client_id;
		this.address = address;
		this.sec_client_name = sec_client_name;
		this.client_id = client_id;
	}
	public int getSec_client_id() {
		return sec_client_id;
	}
	public void setSec_client_id(int sec_client_id) {
		this.sec_client_id = sec_client_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSec_client_name() {
		return sec_client_name;
	}
	public void setSec_client_name(String sec_client_name) {
		this.sec_client_name = sec_client_name;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	@Override
	public String toString() {
		return "SecondaryClientDTO [sec_client_id=" + sec_client_id + ", address=" + address + ", sec_client_name="
				+ sec_client_name + ", client_id=" + client_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + client_id;
		result = prime * result + sec_client_id;
		result = prime * result + ((sec_client_name == null) ? 0 : sec_client_name.hashCode());
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
		SecondaryClientDTO other = (SecondaryClientDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (client_id != other.client_id)
			return false;
		if (sec_client_id != other.sec_client_id)
			return false;
		if (sec_client_name == null) {
			if (other.sec_client_name != null)
				return false;
		} else if (!sec_client_name.equals(other.sec_client_name))
			return false;
		return true;
	}
	
	
}
