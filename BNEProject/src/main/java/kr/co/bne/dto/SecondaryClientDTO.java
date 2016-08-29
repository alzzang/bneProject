package kr.co.bne.dto;

public class SecondaryClientDTO {
	private int sec_client_id;
	private String address;
	private String sec_client_name;
	private int client_id;
	private String telephone;
	
	private int daily_report_id;
	private String client_name;
	private String representative;
	
	public SecondaryClientDTO() {
		super();
	}
	public SecondaryClientDTO(int sec_client_id, String address, String sec_client_name, int client_id,
			String telephone, int daily_report_id, String client_name, String representative) {
		super();
		this.sec_client_id = sec_client_id;
		this.address = address;
		this.sec_client_name = sec_client_name;
		this.client_id = client_id;
		this.telephone = telephone;
		this.daily_report_id = daily_report_id;
		this.client_name = client_name;
		this.representative = representative;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getDaily_report_id() {
		return daily_report_id;
	}
	public void setDaily_report_id(int daily_report_id) {
		this.daily_report_id = daily_report_id;
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
	public String toString() {
		return "SecondaryClientDTO [sec_client_id=" + sec_client_id + ", address=" + address + ", sec_client_name="
				+ sec_client_name + ", client_id=" + client_id + ", telephone=" + telephone + ", daily_report_id="
				+ daily_report_id + ", client_name=" + client_name + ", representative=" + representative + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + client_id;
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + daily_report_id;
		result = prime * result + ((representative == null) ? 0 : representative.hashCode());
		result = prime * result + sec_client_id;
		result = prime * result + ((sec_client_name == null) ? 0 : sec_client_name.hashCode());
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
		SecondaryClientDTO other = (SecondaryClientDTO) obj;
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
		if (daily_report_id != other.daily_report_id)
			return false;
		if (representative == null) {
			if (other.representative != null)
				return false;
		} else if (!representative.equals(other.representative))
			return false;
		if (sec_client_id != other.sec_client_id)
			return false;
		if (sec_client_name == null) {
			if (other.sec_client_name != null)
				return false;
		} else if (!sec_client_name.equals(other.sec_client_name))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}
	
	
}
