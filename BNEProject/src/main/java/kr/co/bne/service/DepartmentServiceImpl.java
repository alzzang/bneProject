package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}