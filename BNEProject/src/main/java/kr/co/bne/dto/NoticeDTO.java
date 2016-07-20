package kr.co.bne.dto;

public class NoticeDTO {
	
	public enum NOTICE_TYPE {POST, CORRECT, COMMENT, APPROVAL};
	
	private String notice_type;
	private int notice_id;
	private String subject;
	private String object;
	private String reg_date;
	private String read_flag;
	
	
	
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getRead_flag() {
		return read_flag;
	}
	public void setRead_flag(String read_flag) {
		this.read_flag = read_flag;
	}
	
}
