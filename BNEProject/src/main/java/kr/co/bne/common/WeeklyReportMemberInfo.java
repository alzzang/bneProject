package kr.co.bne.common;

public class WeeklyReportMemberInfo {

		private String employee_id;
		private String employee_name;
		private String file_position;
		private int post_num;
		
		public WeeklyReportMemberInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public WeeklyReportMemberInfo(String employee_id, String employee_name, String file_position, int post_num) {
			super();
			this.employee_id = employee_id;
			this.employee_name = employee_name;
			this.file_position = file_position;
			this.post_num = post_num;
		}
		public String getEmployee_id() {
			return employee_id;
		}
		public void setEmployee_id(String employee_id) {
			this.employee_id = employee_id;
		}
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
		public int getPost_num() {
			return post_num;
		}
		public void setPost_num(int post_num) {
			this.post_num = post_num;
		}
		@Override
		public String toString() {
			return "WeeklyReportMemberInfo [employee_id=" + employee_id + ", employee_name=" + employee_name
					+ ", file_position=" + file_position + ", post_num=" + post_num + "]";
		}
		
}
