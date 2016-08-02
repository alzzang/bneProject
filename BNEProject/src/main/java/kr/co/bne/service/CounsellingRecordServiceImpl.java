package kr.co.bne.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.CounsellingManageSales;
import kr.co.bne.common.CounsellingSalesGoal;
import kr.co.bne.dao.CounsellingRecordDAO;
import kr.co.bne.dto.CounsellingManageSalesDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.EmployeeDTO;


@Service
public class CounsellingRecordServiceImpl implements CounsellingRecordService {
	
	@Autowired CounsellingRecordDAO counsellingRecordDAO;
	
	
	@Override
	public void setCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO) {
		// TODO Auto-generated method stub
		
		counsellingRecordDAO.insertCounsellingRecord(counsellingRecordDTO);
		
		
	}


	@Override
	public CounsellingRecordDTO getCounsellingRecord(int counsellingId) {
		// TODO Auto-generated method stub
		return counsellingRecordDAO.selectCounsellingRecord(counsellingId);
	}
	
	public List<EmployeeDTO> getManageSales(int departmentId){
		return counsellingRecordDAO.selectManageSales(departmentId);
	}

	public List<CounsellingManageSalesDTO> getManageSalesList(CounsellingManageSales cm){
		return counsellingRecordDAO.selectManageSalesList(cm);
	}


	@Override
	public void insertUpdateSalesGoal(CounsellingSalesGoal csg) {
		/* insert */
		System.out.println("Here Service.. getIsEmpty = "+csg.getIs_empty());
		if(csg.getIs_empty()==1){
			
			counsellingRecordDAO.insertSalesGoal(csg);
			
		/* update */
		}else{
			counsellingRecordDAO.updateSalesGoal(csg);
		}
		
		
	}
	
}
