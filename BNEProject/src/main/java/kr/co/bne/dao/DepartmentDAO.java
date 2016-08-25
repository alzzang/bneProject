package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.common.DepartmentTeamList;

public interface DepartmentDAO {

	List<DepartmentTeamList> getDepartmentList(int startIdx, int perContentNum, HashMap<String, String> params);
	int getPagingNum_DepartmentList(int perContentNum, HashMap<String, String> params);

}
