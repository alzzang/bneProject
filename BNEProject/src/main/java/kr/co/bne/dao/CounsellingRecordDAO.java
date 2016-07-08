package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.dto.CounsellingRecordDTO;

public interface CounsellingRecordDAO {

public void insertCounsellingRecord(CounsellingRecordDTO counsellingRecord);
public CounsellingRecordDTO selectCounsellingRecord(int counsellingId);
	
}
