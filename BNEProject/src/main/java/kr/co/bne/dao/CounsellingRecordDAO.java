package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.common.CounsellingManageSales;
import kr.co.bne.common.CounsellingSalesGoal;
import kr.co.bne.dto.CounsellingManageSalesDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.EmployeeDTO;

public interface CounsellingRecordDAO {

public void insertCounsellingRecord(CounsellingRecordDTO counsellingRecord);
public CounsellingRecordDTO selectCounsellingRecord(int counsellingId);
public List<EmployeeDTO> selectManageSales(int departmentId);
public List<CounsellingManageSalesDTO> selectManageSalesList(CounsellingManageSales cm);
public void insertSalesGoal(CounsellingSalesGoal csg);
public void updateSalesGoal(CounsellingSalesGoal csg);
	
}
