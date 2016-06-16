package kr.co.bne.dto;

public class EmployeeDTO {
	private int employee_id;
	private String employee_name;
	private String password;
	private int department_id;
	private int manager_id;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDTO(int employee_id, String employee_name, String password, int department_id, int manager_id) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.password = password;
		this.department_id = department_id;
		this.manager_id = manager_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
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
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department_id;
		result = prime * result + employee_id;
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + manager_id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		EmployeeDTO other = (EmployeeDTO) obj;
		if (department_id != other.department_id)
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		if (manager_id != other.manager_id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [employee_id=" + employee_id + ", employee_name=" + employee_name + ", password=" + password
				+ ", department_id=" + department_id + ", manager_id=" + manager_id + "]";
	}
	
	
}
