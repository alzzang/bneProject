package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DepartmentTeamList;
import kr.co.bne.dao.DepartmentDAO;
import kr.co.bne.dto.DepartmentDTO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDAO departmentDAO;
	
	@Override
	public List<DepartmentDTO> getDepartmentList() {
		return departmentDAO.getDepartmentList();
	}
	@Override
	public int searchManager(String manager_id) {
		// TODO Auto-generated method stub
		return departmentDAO.getManagerCount(manager_id);
	}
	@Override
	public boolean addDepartment(DepartmentDTO ddto) {
		boolean status = false;
		boolean status1 = false;
		DepartmentTeamList deptlist=new DepartmentTeamList();
	
		
	       status = departmentDAO.addDepartment(ddto);
	   	deptlist.setDepartment_id(ddto.getDepartment_id());
		deptlist.setManager_id(ddto.getManager_id());
	       status1=	departmentDAO.updateManager(deptlist);
	        return status && status1;
	}
}
