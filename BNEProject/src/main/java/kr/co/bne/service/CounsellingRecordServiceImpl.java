package kr.co.bne.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.CounsellingRecordDAO;
import kr.co.bne.dto.CounsellingRecordDTO;


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
	


}
