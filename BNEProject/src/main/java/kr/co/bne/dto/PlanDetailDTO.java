package kr.co.bne.dto;

public class PlanDetailDTO {
	private int plan_detail_id;
	private String weekly_report_id;
	private String content;
	private String start_time;
	private String end_time;
	public PlanDetailDTO(int plan_detail_id, String weekly_report_id, String content, String start_time,
			String end_time) {
		super();
		this.plan_detail_id = plan_detail_id;
		this.weekly_report_id = weekly_report_id;
		this.content = content;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public PlanDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPlan_detail_id() {
		return plan_detail_id;
	}
	public void setPlan_detail_id(int plan_detail_id) {
		this.plan_detail_id = plan_detail_id;
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
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "PlanDetailDTO [plan_detail_id=" + plan_detail_id + ", weekly_report_id=" + weekly_report_id
				+ ", content=" + content + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((end_time == null) ? 0 : end_time.hashCode());
		result = prime * result + plan_detail_id;
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
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
		PlanDetailDTO other = (PlanDetailDTO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (end_time == null) {
			if (other.end_time != null)
				return false;
		} else if (!end_time.equals(other.end_time))
			return false;
		if (plan_detail_id != other.plan_detail_id)
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (weekly_report_id == null) {
			if (other.weekly_report_id != null)
				return false;
		} else if (!weekly_report_id.equals(other.weekly_report_id))
			return false;
		return true;
	}
	
	

}
