package kr.co.bne.dto;

public class DepartmentDTO {
	private int department_id;
	private String department_name;
	private int manager_id;
	public DepartmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartmentDTO(int department_id, String department_name, int manager_id) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "DepartmentDTO [department_id=" + department_id + ", department_name=" + department_name
				+ ", manager_id=" + manager_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department_id;
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + manager_id;
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
		DepartmentDTO other = (DepartmentDTO) obj;
		if (department_id != other.department_id)
			return false;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (manager_id != other.manager_id)
			return false;
		return true;
	}
	
	
}
