package kr.co.bne.dto;

public class DailyReportDetailDTO {
	private String daily_report_id;
	private String employee_id;
	private String employee_name;
	private String file_position;
	private String title;
	private String reg_date;
	private int drsales;
	private int before_gauge;
	private int after_gauge;
	private String content;
	private int wpsales;
	private String manager_comment;
	private int approval_flag;
	private String manager_name;
	private String manager_file_position;
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getFile_position() {
		return file_position;
	}
	public void setFile_position(String file_position) {
		this.file_position = file_position;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getDrsales() {
		return drsales;
	}
	public void setDrsales(int drsales) {
		this.drsales = drsales;
	}
	public int getBefore_gauge() {
		return before_gauge;
	}
	public void setBefore_gauge(int before_gauge) {
		this.before_gauge = before_gauge;
	}
	public int getAfter_gauge() {
		return after_gauge;
	}
	public void setAfter_gauge(int after_gauge) {
		this.after_gauge = after_gauge;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWpsales() {
		return wpsales;
	}
	public void setWpsales(int wpsales) {
		this.wpsales = wpsales;
	}
	public String getManager_comment() {
		return manager_comment;
	}
	public void setManager_comment(String manager_comment) {
		this.manager_comment = manager_comment;
	}
	public int getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(int approval_flag) {
		this.approval_flag = approval_flag;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_file_position() {
		return manager_file_position;
	}
	public void setManager_file_position(String manager_file_position) {
		this.manager_file_position = manager_file_position;
	}
	

	@Override
	public String toString() {
		return "DailyReportDetailDTO [daily_report_id=" + daily_report_id + ", employee_id=" + employee_id
				+ ", employee_name=" + employee_name + ", file_position=" + file_position + ", title=" + title
				+ ", reg_date=" + reg_date + ", drsales=" + drsales + ", before_gauge=" + before_gauge
				+ ", after_gauge=" + after_gauge + ", content=" + content + ", wpsales=" + wpsales
				+ ", manager_comment=" + manager_comment + ", approval_flag=" + approval_flag + ", manager_name="
				+ manager_name + ", manager_file_position=" + manager_file_position + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + after_gauge;
		result = prime * result + approval_flag;
		result = prime * result + before_gauge;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + drsales;
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + ((file_position == null) ? 0 : file_position.hashCode());
		result = prime * result + ((manager_comment == null) ? 0 : manager_comment.hashCode());
		result = prime * result + ((manager_file_position == null) ? 0 : manager_file_position.hashCode());
		result = prime * result + ((manager_name == null) ? 0 : manager_name.hashCode());
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + wpsales;
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
		DailyReportDetailDTO other = (DailyReportDetailDTO) obj;
		if (after_gauge != other.after_gauge)
			return false;
		if (approval_flag != other.approval_flag)
			return false;
		if (before_gauge != other.before_gauge)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (drsales != other.drsales)
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		if (file_position == null) {
			if (other.file_position != null)
				return false;
		} else if (!file_position.equals(other.file_position))
			return false;
		if (manager_comment == null) {
			if (other.manager_comment != null)
				return false;
		} else if (!manager_comment.equals(other.manager_comment))
			return false;
		if (manager_file_position == null) {
			if (other.manager_file_position != null)
				return false;
		} else if (!manager_file_position.equals(other.manager_file_position))
			return false;
		if (manager_name == null) {
			if (other.manager_name != null)
				return false;
		} else if (!manager_name.equals(other.manager_name))
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (wpsales != other.wpsales)
			return false;
		return true;
	}
	public DailyReportDetailDTO(){
		
	}
	public DailyReportDetailDTO(String employee_name, String file_position, String title, String reg_date, int drsales,
			int before_gauge, int after_gauge, String content, int wpsales, String manager_comment, int approval_flag,
			String manager_name, String manager_file_position) {
		super();
		this.employee_name = employee_name;
		this.file_position = file_position;
		this.title = title;
		this.reg_date = reg_date;
		this.drsales = drsales;
		this.before_gauge = before_gauge;
		this.after_gauge = after_gauge;
		this.content = content;
		this.wpsales = wpsales;
		this.manager_comment = manager_comment;
		this.approval_flag = approval_flag;
		this.manager_name = manager_name;
		this.manager_file_position = manager_file_position;
	}
	public String getDaily_report_id() {
		return daily_report_id;
	}
	public void setDaily_report_id(String daily_report_id) {
		this.daily_report_id = daily_report_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	
	
	
}
