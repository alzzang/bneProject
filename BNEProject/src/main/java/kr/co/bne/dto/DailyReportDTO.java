package kr.co.bne.dto;

import java.util.List;

public class DailyReportDTO {
	private int daily_report_id;
	private int department_id;
	private int employee_id;
	private String title;
	private String reg_date;
	private int sales;
	private int before_gauge;
	private int after_gauge;
	private String content;
	private String manager_comment;
	private int approval_flag;
	public DailyReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DailyReportDTO(int daily_report_id, int department_id, int employee_id, String title, String reg_date,
			int sales, int before_gauge, int after_gauge, String content, String manager_comment, int approval_flag) {
		super();
		this.daily_report_id = daily_report_id;
		this.department_id = department_id;
		this.employee_id = employee_id;
		this.title = title;
		this.reg_date = reg_date;
		this.sales = sales;
		this.before_gauge = before_gauge;
		this.after_gauge = after_gauge;
		this.content = content;
		this.manager_comment = manager_comment;
		this.approval_flag = approval_flag;
	}
	public int getDaily_report_id() {
		return daily_report_id;
	}
	public void setDaily_report_id(int daily_report_id) {
		this.daily_report_id = daily_report_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
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
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + after_gauge;
		result = prime * result + approval_flag;
		result = prime * result + before_gauge;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + daily_report_id;
		result = prime * result + department_id;
		result = prime * result + employee_id;
		result = prime * result + ((manager_comment == null) ? 0 : manager_comment.hashCode());
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + sales;
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
		DailyReportDTO other = (DailyReportDTO) obj;
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
		if (daily_report_id != other.daily_report_id)
			return false;
		if (department_id != other.department_id)
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (manager_comment == null) {
			if (other.manager_comment != null)
				return false;
		} else if (!manager_comment.equals(other.manager_comment))
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (sales != other.sales)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DailyReportDTO [daily_report_id=" + daily_report_id + ", department_id=" + department_id
				+ ", employee_id=" + employee_id + ", title=" + title + ", reg_date=" + reg_date + ", sales=" + sales
				+ ", before_gauge=" + before_gauge + ", after_gauge=" + after_gauge + ", content=" + content
				+ ", manager_comment=" + manager_comment + ", approval_flag=" + approval_flag + "]";
	}
	
	
}
