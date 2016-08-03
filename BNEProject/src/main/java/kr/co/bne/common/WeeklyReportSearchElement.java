package kr.co.bne.common;

import kr.co.bne.dto.WeeklyReportDTO;

public class WeeklyReportSearchElement extends WeeklyReportDTO {
		private String department_name;
		private String employee_name;
		private String date_period;
		
		public WeeklyReportSearchElement() {
			super();
			// TODO Auto-generated constructor stub
		}
		public WeeklyReportSearchElement(String weekly_report_id, String employee_id, String title, String reg_date, int sales_goal, int sales) {
			super(weekly_report_id, employee_id, title, reg_date, sales_goal, sales);
		}
		
		public WeeklyReportSearchElement(String department_name, String employee_name, String date_period) {
			super();
			this.department_name = department_name;
			this.employee_name = employee_name;
			this.date_period = date_period;
		}
		
		public String getDepartment_name() {
			return department_name;
		}
		public void setDepartment_name(String department_name) {
			this.department_name = department_name;
		}
		public String getEmployee_name() {
			return employee_name;
		}
		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}
		public String getDate_period() {
			return date_period;
		}
		public void setDate_period(String date_period) {
			this.date_period = date_period;
		}
		@Override
		public String toString() {
			return "SearchWeeklyReportElement [department_name=" + department_name + ", employee_name=" + employee_name
					+ ", date_period=" + date_period + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((date_period == null) ? 0 : date_period.hashCode());
			result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
			result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			WeeklyReportSearchElement other = (WeeklyReportSearchElement) obj;
			if (date_period == null) {
				if (other.date_period != null)
					return false;
			} else if (!date_period.equals(other.date_period))
				return false;
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
			return true;
		}
		
		
		
	

}
