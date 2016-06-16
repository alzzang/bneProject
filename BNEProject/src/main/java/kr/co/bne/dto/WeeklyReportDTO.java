package kr.co.bne.dto;

public class WeeklyReportDTO {
	private int weekly_report_id;
	private int employee_id;
	private int department_id;
	private String title;
	private String reg_date;
	private int saleGoal;
	private int sales;
	public WeeklyReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WeeklyReportDTO(int weekly_report_id, int employee_id, int department_id, String title, String reg_date,
			int saleGoal, int sales) {
		super();
		this.weekly_report_id = weekly_report_id;
		this.employee_id = employee_id;
		this.department_id = department_id;
		this.title = title;
		this.reg_date = reg_date;
		this.saleGoal = saleGoal;
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "WeeklyReportDTO [weekly_report_id=" + weekly_report_id + ", employee_id=" + employee_id
				+ ", department_id=" + department_id + ", title=" + title + ", reg_date=" + reg_date + ", saleGoal="
				+ saleGoal + ", sales=" + sales + "]";
	}
	public int getWeekly_report_id() {
		return weekly_report_id;
	}
	public void setWeekly_report_id(int weekly_report_id) {
		this.weekly_report_id = weekly_report_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
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
	public int getSaleGoal() {
		return saleGoal;
	}
	public void setSaleGoal(int saleGoal) {
		this.saleGoal = saleGoal;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department_id;
		result = prime * result + employee_id;
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + saleGoal;
		result = prime * result + sales;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + weekly_report_id;
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
		WeeklyReportDTO other = (WeeklyReportDTO) obj;
		if (department_id != other.department_id)
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (saleGoal != other.saleGoal)
			return false;
		if (sales != other.sales)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (weekly_report_id != other.weekly_report_id)
			return false;
		return true;
	}
	
	
	
	
}