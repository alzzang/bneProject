package kr.co.bne.dto;

import java.util.List;

public class WeeklyReportDetailDTO {
	private WeeklyReportDTO weeklyReportDTO;
	private List<WeeklyPlanDTO> weeklyPlanDTOList;
	private List<PlanDetailDTO> planDetailDTOList;
	private int link_id;

	
	public WeeklyReportDetailDTO() {
		super();
	}
	
	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}


	public WeeklyReportDetailDTO(WeeklyReportDTO weeklyReportDTO, List<WeeklyPlanDTO> weeklyPlanDTOList,
			List<PlanDetailDTO> planDetailDTOList) {
		super();
		this.weeklyReportDTO = weeklyReportDTO;
		this.weeklyPlanDTOList = weeklyPlanDTOList;
		this.planDetailDTOList = planDetailDTOList;
	}

	public WeeklyReportDTO getWeeklyReportDTO() {
		return weeklyReportDTO;
	}

	public void setWeeklyReportDTO(WeeklyReportDTO weeklyReportDTO) {
		this.weeklyReportDTO = weeklyReportDTO;
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
		return "WeeklyReportDetailDTO [weeklyReportDTO=" + weeklyReportDTO + ", weeklyPlanDTOList=" + weeklyPlanDTOList
				+ ", planDetailDTOList=" + planDetailDTOList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planDetailDTOList == null) ? 0 : planDetailDTOList.hashCode());
		result = prime * result + ((weeklyPlanDTOList == null) ? 0 : weeklyPlanDTOList.hashCode());
		result = prime * result + ((weeklyReportDTO == null) ? 0 : weeklyReportDTO.hashCode());
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
		if (weeklyReportDTO == null) {
			if (other.weeklyReportDTO != null)
				return false;
		} else if (!weeklyReportDTO.equals(other.weeklyReportDTO))
			return false;
		return true;
	}
	
	
}
