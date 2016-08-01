package kr.co.bne.service;

import java.util.List;

import kr.co.bne.common.CounsellingManageSales;
import kr.co.bne.common.CounsellingSalesGoal;
import kr.co.bne.dto.CounsellingManageSalesDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.EmployeeDTO;

public interface CounsellingRecordService {
	public void setCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO);
	public CounsellingRecordDTO getCounsellingRecord(int counsellingId);
	
	public List<EmployeeDTO> getManageSales(int departmentId);
	public List<CounsellingManageSalesDTO> getManageSalesList(CounsellingManageSales cm);
	public void insertUpdateSalesGoal(CounsellingSalesGoal csg);
}
