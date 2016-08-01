package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import kr.co.bne.dao.EmployeeDAO;
import kr.co.bne.dto.EmployeeDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	EmployeeDAO employeeDAO;

	@Override
	public EmployeeDTO validCheck(String id, String rawPassword) {
		EmployeeDTO employeeDTO = employeeDAO.selectEmployee(id);
			
		if (employeeDTO != null) {
			if (passwordEncoder.matches(rawPassword, employeeDTO.getPassword())) {
				return employeeDTO;
			}
		}
		return null;
	}

	@Override
	public void modifyPassword(String id, String rawPassword) {
		// TODO Auto-generated method stub
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("id", id);
		info.put("password", passwordEncoder.encode(rawPassword));
		employeeDAO.updatePassword(info);
	}

	@Override
	public void modifyFilePosition(String id, String filePosition) {
		// TODO Auto-generated method stub
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("id", id);
		info.put("filePosition", filePosition);
		employeeDAO.updateFile(info);
	}

	@Override
	public void modifyFilePosition(String id) {
		// TODO Auto-generated method stub
		employeeDAO.updateFile(id);
	}

	@Override
	public List<EmployeeDTO> getTeamMember(String position,int departmentId) {
		// TODO Auto-generated method stub
		
		if(position.equals("manager")){
			return employeeDAO.selectTeamMember(departmentId);	
		}
		
	return null;
	}

}
