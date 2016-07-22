package kr.co.bne.dto;

import java.util.List;

public class WeeklyReportDetailDTO {
	private WeeklyReportName weeklyReportName;
	private List<WeeklyPlanDTO> weeklyPlanDTOList;
	private List<PlanDetailDTO> planDetailDTOList;
	public WeeklyReportDetailDTO() {
		super();
	}
	public WeeklyReportDetailDTO(WeeklyReportName weeklyReportName, List<WeeklyPlanDTO> weeklyPlanDTOList,
			List<PlanDetailDTO> planDetailDTOList) {
		super();
		this.weeklyReportName = weeklyReportName;
		this.weeklyPlanDTOList = weeklyPlanDTOList;
		this.planDetailDTOList = planDetailDTOList;
	}
	public WeeklyReportName getWeeklyReportName() {
		return weeklyReportName;
	}
	public void setWeeklyReportName(WeeklyReportName weeklyReportName) {
		this.weeklyReportName = weeklyReportName;
	}
	public List<WeeklyPlanDTO> getWeeklyPlanDTOList() {
		return weeklyPlanDTOList;
	}
	public void setWeeklyPlanDTOList(List<WeeklyPlanDTO> weeklyPlanDTOList) {
		this.weeklyPlanDTOList = weeklyPlanDTOList;
	}
	public List<PlanDetailDTO> getPlanDetailDTOList() {
		return planDetailDTOList;
	}
	public void setPlanDetailDTOList(List<PlanDetailDTO> planDetailDTOList) {
		this.planDetailDTOList = planDetailDTOList;
	}
	@Override
	public String toString() {
		return "WeeklyReportDetailDTO [weeklyReportName=" + weeklyReportName + ", weeklyPlanDTOList="
				+ weeklyPlanDTOList + ", planDetailDTOList=" + planDetailDTOList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planDetailDTOList == null) ? 0 : planDetailDTOList.hashCode());
		result = prime * result + ((weeklyPlanDTOList == null) ? 0 : weeklyPlanDTOList.hashCode());
		result = prime * result + ((weeklyReportName == null) ? 0 : weeklyReportName.hashCode());
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
		WeeklyReportDetailDTO other = (WeeklyReportDetailDTO) obj;
		if (planDetailDTOList == null) {
			if (other.planDetailDTOList != null)
				return false;
		} else if (!planDetailDTOList.equals(other.planDetailDTOList))
			return false;
		if (weeklyPlanDTOList == null) {
			if (other.weeklyPlanDTOList != null)
				return false;
		} else if (!weeklyPlanDTOList.equals(other.weeklyPlanDTOList))
			return false;
		if (weeklyReportName == null) {
			if (other.weeklyReportName != null)
				return false;
		} else if (!weeklyReportName.equals(other.weeklyReportName))
			return false;
		return true;
	}

	
	
}
