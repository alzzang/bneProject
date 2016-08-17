package kr.co.bne.common;

import kr.co.bne.dto.WeeklyReportDTO;

public class WeeklyReportSearchElement extends WeeklyReportDTO {
		private int idx;
		private String department_name;
		private String employee_name;
		private String start_date;
		private String end_date;
		public WeeklyReportSearchElement() {
			super();
			// TODO Auto-generated constructor stub
		}
		public WeeklyReportSearchElement(String weekly_report_id, String employee_id, String title, String reg_date,
				int sales_goal, int sales) {
			super(weekly_report_id, employee_id, title, reg_date, sales_goal, sales);
			// TODO Auto-generated constructor stub
		}
		public WeeklyReportSearchElement(int idx, String department_name, String employee_name, String start_date,
				String end_date) {
			super();
			this.idx = idx;
			this.department_name = department_name;
			this.employee_name = employee_name;
			this.start_date = start_date;
			this.end_date = end_date;
		}
		public int getIdx() {
			return idx;
		}
		public void setIdx(int idx) {
			this.idx = idx;
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
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		@Override
		public String toString() {
			return "WeeklyReportSearchElement [idx=" + idx + ", department_name=" + department_name + ", employee_name="
					+ employee_name + ", start_date=" + start_date + ", end_date=" + end_date + ", getIdx()=" + getIdx()
					+ ", getDepartment_name()=" + getDepartment_name() + ", getEmployee_name()=" + getEmployee_name()
					+ ", getStart_date()=" + getStart_date() + ", getEnd_date()=" + getEnd_date()
					+ ", getWeekly_report_id()=" + getWeekly_report_id() + ", getEmployee_id()=" + getEmployee_id()
					+ ", getTitle()=" + getTitle() + ", getReg_date()=" + getReg_date() + ", getSales_goal()="
					+ getSales_goal() + ", getSales()=" + getSales() + ", toString()=" + super.toString()
					+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
			result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
			result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
			result = prime * result + idx;
			result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
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
			if (end_date == null) {
				if (other.end_date != null)
					return false;
			} else if (!end_date.equals(other.end_date))
				return false;
			if (idx != other.idx)
				return false;
			if (start_date == null) {
				if (other.start_date != null)
					return false;
			} else if (!start_date.equals(other.start_date))
				return false;
			return true;
		}

		
		

}
