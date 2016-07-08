package kr.co.bne.dto;

public class DailyReportEmployeeDTO {
	private String department_name;
	private String employee_name;
	private int sales_goal;
	private String employee_id;
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
	public int getSales_goal() {
		return sales_goal;
	}
	public void setSales_goal(int sales_goal) {
		this.sales_goal = sales_goal;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + sales_goal;
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
		DailyReportEmployeeDTO other = (DailyReportEmployeeDTO) obj;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		if (sales_goal != other.sales_goal)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DailyReportEmployeeDTO [department_name=" + department_name + ", employee_name=" + employee_name
				+ ", sales_goal=" + sales_goal + ", employee_id=" + employee_id + "]";
	}
	public DailyReportEmployeeDTO(String department_name, String employee_name, int sales_goal, String employee_id) {
		this.department_name = department_name;
		this.employee_name = employee_name;
		this.sales_goal = sales_goal;
		this.employee_id = employee_id;
	}
	public DailyReportEmployeeDTO(){
		
	}
	
}
