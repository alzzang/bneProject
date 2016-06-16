package kr.co.bne.dto;

public class WeeklyPlanDTO {
	private int weekly_plan_id;
	private int weekly_report_id;
	private String content;
	private int sales;
	private String plan_date;
	public WeeklyPlanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WeeklyPlanDTO(int weekly_plan_id, int weekly_report_id, String content, int sales, String plan_date) {
		super();
		this.weekly_plan_id = weekly_plan_id;
		this.weekly_report_id = weekly_report_id;
		this.content = content;
		this.sales = sales;
		this.plan_date = plan_date;
	}
	public int getWeekly_plan_id() {
		return weekly_plan_id;
	}
	public void setWeekly_plan_id(int weekly_plan_id) {
		this.weekly_plan_id = weekly_plan_id;
	}
	public int getWeekly_report_id() {
		return weekly_report_id;
	}
	public void setWeekly_report_id(int weekly_report_id) {
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
	public String getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(String plan_date) {
		this.plan_date = plan_date;
	}
	@Override
	public String toString() {
		return "WeeklyPlanDTO [weekly_plan_id=" + weekly_plan_id + ", weekly_report_id=" + weekly_report_id
				+ ", content=" + content + ", sales=" + sales + ", plan_date=" + plan_date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((plan_date == null) ? 0 : plan_date.hashCode());
		result = prime * result + sales;
		result = prime * result + weekly_plan_id;
		result = prime * result + weekly_report_id;
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
		if (plan_date == null) {
			if (other.plan_date != null)
				return false;
		} else if (!plan_date.equals(other.plan_date))
			return false;
		if (sales != other.sales)
			return false;
		if (weekly_plan_id != other.weekly_plan_id)
			return false;
		if (weekly_report_id != other.weekly_report_id)
			return false;
		return true;
	}
	
	
}
