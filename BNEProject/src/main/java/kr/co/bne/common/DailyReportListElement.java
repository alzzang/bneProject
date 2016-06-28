package kr.co.bne.common;

public class DailyReportListElement {

	private int idx;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	private String employee_id;
	private String employee_name;
	private String title;
	private String reg_date;
	private int approval_flag;

	
	
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setPuemployee_name(String employee_name) {
		this.employee_name = employee_name;
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
	public int getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(int approval_flag) {
		this.approval_flag = approval_flag;
	}
	
}
