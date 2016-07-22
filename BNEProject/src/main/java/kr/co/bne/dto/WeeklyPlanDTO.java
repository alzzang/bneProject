package kr.co.bne.dto;

public class WeeklyPlanDTO {
	private int weekly_plan_id;
	private String weekly_report_id;
	private String content;
	private int sales;
	private String reg_date;
	public WeeklyPlanDTO() {
		super();
	}
	public WeeklyPlanDTO(int weekly_plan_id, String weekly_report_id, String content, int sales, String reg_date) {
		super();
		this.weekly_plan_id = weekly_plan_id;
		this.weekly_report_id = weekly_report_id;
		this.content = content;
		this.sales = sales;
		this.reg_date = reg_date;
	}
	public int getWeekly_plan_id() {
		return weekly_plan_id;
	}
	public void setWeekly_plan_id(int weekly_plan_id) {
		this.weekly_plan_id = weekly_plan_id;
	}
	public String getWeekly_report_id() {
		return weekly_report_id;
	}
	public void setWeekly_report_id(String weekly_report_id) {
		this.weekly_report_id = weekly_report_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "WeeklyPlanDTO [weekly_plan_id=" + weekly_plan_id + ", weekly_report_id=" + weekly_report_id
				+ ", content=" + content + ", sales=" + sales + ", reg_date=" + reg_date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((reg_date == null) ? 0 : reg_date.hashCode());
		result = prime * result + sales;
		result = prime * result + weekly_plan_id;
		result = prime * result + ((weekly_report_id == null) ? 0 : weekly_report_id.hashCode());
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
		WeeklyPlanDTO other = (WeeklyPlanDTO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!reg_date.equals(other.reg_date))
			return false;
		if (sales != other.sales)
			return false;
		if (weekly_plan_id != other.weekly_plan_id)
			return false;
		if (weekly_report_id == null) {
			if (other.weekly_report_id != null)
				return false;
		} else if (!weekly_report_id.equals(other.weekly_report_id))
			return false;
		return true;
	}
	
	
}
