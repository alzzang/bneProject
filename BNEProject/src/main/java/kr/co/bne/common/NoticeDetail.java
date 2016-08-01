package kr.co.bne.common;

public class NoticeDetail {
	private int importance_level;
	private String notice_type;
	private int notice_id;
	private String reg_date;
	private String passtime;
	private String employee_name;
	private String content;
	private int link_id;
	private String  subject;
	public int getImportance_level() {
		return importance_level;
	}
	public void setImportance_level(int importance_level) {
		this.importance_level = importance_level;
	}
	public String getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getPasstime() {
		return passtime;
	}
	public void setPasstime(String passtime) {
		this.passtime = passtime;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getLink_id() {
		return link_id;
	}
	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}
	@Override
	public String toString() {
		return "NoticeDetail [importance_level=" + importance_level + ", notice_type=" + notice_type + ", notice_id="
				+ notice_id + ", reg_date=" + reg_date + ", passtime=" + passtime + ", employee_name=" + employee_name
				+ ", content=" + content + ", link_id=" + link_id + "]";
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}
