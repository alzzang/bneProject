package kr.co.bne.dto;

public class WeeklyReportName {
	private int weekly_report_id;
	private String title;
	private String reg_date;
	private int saleGoal;
	private int sales;
	private String employee_name;
	private String department_name;
	public WeeklyReportName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WeeklyReportName(int weekly_report_id, String title, String reg_date, int saleGoal, int sales,
			String employee_name, String department_name) {
		super();
		this.weekly_report_id = weekly_report_id;
		this.title = title;
		this.reg_date = reg_date;
		this.saleGoal = saleGoal;
		this.sales = sales;
		this.employee_name = employee_name;
		this.department_name = department_name;
	}
	public int getWeekly_report_id() {
		return weekly_report_id;
	}
	public void setWeekly_report_id(int weekly_report_id) {
		this.weekly_report_id = weekly_report_id;
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
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		return "WeeklyReportName [weekly_report_id=" + weekly_report_id + ", title=" + title + ", reg_date=" + reg_date
				+ ", saleGoal=" + saleGoal + ", sales=" + sales + ", employee_name=" + employee_name
				+ ", department_name=" + department_name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
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
		WeeklyReportName other = (WeeklyReportName) obj;
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
