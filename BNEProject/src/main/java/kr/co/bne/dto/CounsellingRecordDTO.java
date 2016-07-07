package kr.co.bne.dto;

public class CounsellingRecordDTO  {
	private int counsel_id;
	private int department_id;
	private int daily_report_id;
	private String reg_date;
	private String title;
	private String content;
	private int sec_client_id;
	
	public CounsellingRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CounsellingRecordDTO(int counsel_id, int department_id, int daily_report_id, String reg_date, String title,
			String content, int sec_client_id) {
		super();
		this.counsel_id = counsel_id;
		this.department_id = department_id;
		this.daily_report_id = daily_report_id;
		this.reg_date = reg_date;
		this.title = title;
		this.content = content;
		this.sec_client_id = sec_client_id;
	}
	public int getCounsel_id() {
		return counsel_id;
	}
	public void setCounsel_id(int counsel_id) {
		this.counsel_id = counsel_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getDaily_report_id() {
		return daily_report_id;
	}
	public void setDaily_report_id(int daily_report_id) {
		this.daily_report_id = daily_report_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSec_client_id() {
		return sec_client_id;
	}
	public void setSec_client_id(int sec_client_id) {
		this.sec_client_id = sec_client_id;
	}
	@Override
	public String toString() {
		return "CounsellingRecordDTO [counsel_id=" + counsel_id + ", department_id=" + department_id
				+ ", daily_report_id=" + daily_report_id + ", reg_date=" + reg_date + ", title=" + title + ", content="
				+ content + ", sec_client_id=" + sec_client_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + counsel_id;
		result = prime * result + daily_report_id;
		result = prime * result + department_id;
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + sec_client_id;
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
		CounsellingRecordDTO other = (CounsellingRecordDTO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (counsel_id != other.counsel_id)
			return false;
		if (daily_report_id != other.daily_report_id)
			return false;
		if (department_id != other.department_id)
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (sec_client_id != other.sec_client_id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
