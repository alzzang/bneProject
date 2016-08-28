package kr.co.bne.dto;

public class WeeklyReportDTO {
	private String weekly_report_id;
	private String employee_id;
	private String title;
	private String reg_date;
	private int sales_goal;
	private int sales;
	private int link_id;
	public WeeklyReportDTO() {
		super();
	}

	public WeeklyReportDTO(String weekly_report_id, String employee_id, String title, String reg_date, int sales_goal,
			int sales) {
		super();
		this.weekly_report_id = weekly_report_id;
		this.employee_id = employee_id;
		this.title = title;
		this.reg_date = reg_date;
		this.sales_goal = sales_goal;
		this.sales = sales;
	}

	public String getWeekly_report_id() {
		return weekly_report_id;
	}

	public void setWeekly_report_id(String weekly_report_id) {
		this.weekly_report_id = weekly_report_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
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

	public int getSales_goal() {
		return sales_goal;
	}

	public void setSales_goal(int sales_goal) {
		this.sales_goal = sales_goal;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "WeeklyReportDTO [weekly_report_id=" + weekly_report_id + ", employee_id=" + employee_id + ", title="
				+ title + ", reg_date=" + reg_date + ", sales_goal=" + sales_goal + ", sales=" + sales + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + sales;
		result = prime * result + sales_goal;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((weekly_report_id == null) ? 0 : weekly_report_id.hashCode());
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
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (sales != other.sales)
			return false;
		if (sales_goal != other.sales_goal)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (weekly_report_id == null) {
			if (other.weekly_report_id != null)
				return false;
		} else if (!weekly_report_id.equals(other.weekly_report_id))
			return false;
		return true;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

	
}
