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
				int sales_goal, int sales, int link_id) {
			super(weekly_report_id, employee_id, title, reg_date, sales_goal, sales, link_id);
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
					+ employee_name + ", start_date=" + start_date + ", end_date=" + end_date
					+ ", getWeekly_report_id()=" + getWeekly_report_id() + ", getEmployee_id()=" + getEmployee_id()
					+ ", getTitle()=" + getTitle() + ", getReg_date()=" + getReg_date() + ", getSales_goal()="
					+ getSales_goal() + ", getSales()=" + getSales() + ", getLink_id()=" + getLink_id()
					+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ "]";
		}

		
		
}
