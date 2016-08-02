package kr.co.bne.dto;

public class EmployeeDTO {
	private String employee_id;
	private String employee_name;
	private String password;
	private String department_name;
	private String position;
	private String file_position;
	private int department_id;
	private String mobile_phone;
	private String email;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFile_position() {
		return file_position;
	}
	public void setFile_position(String file_position) {
		this.file_position = file_position;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDTO [employee_id=");
		builder.append(employee_id);
		builder.append(", employee_name=");
		builder.append(employee_name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", department_name=");
		builder.append(department_name);
		builder.append(", position=");
		builder.append(position);
		builder.append(", file_position=");
		builder.append(file_position);
		builder.append(", department_id=");
		builder.append(department_id);
		builder.append(", mobile_phone=");
		builder.append(mobile_phone);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
