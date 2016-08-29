package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.DepartmentDTO;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

   @Autowired
   SqlSession sqlSession;
   
   @Override
   public List<DepartmentDTO> getDepartmentList() {
      return sqlSession.selectList("kr.co.bne.mapper.Department.getDepartmentList");
   }
   
}
