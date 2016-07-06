package kr.co.bne.service;

import java.util.List;

import kr.co.bne.dto.CounsellingRecordDTO;

public interface CounsellingRecordService {
	public void setCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO);
	public CounsellingRecordDTO getCounsellingRecord(int counsellingId);
	
	
}
