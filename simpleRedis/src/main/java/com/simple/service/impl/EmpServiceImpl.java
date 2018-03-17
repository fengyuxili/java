package com.simple.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.entity.Emp;
import com.simple.mapper.EmpMapper;
import com.simple.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpMapper empMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return empMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Emp record) {
		return empMapper.insert(record);
	}

	@Override
	public int insertSelective(Emp record) {
		return empMapper.insertSelective(record);
	}

	@Override
	public Emp selectByPrimaryKey(Integer id) {
		return empMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Emp record) {
		return empMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Emp record) {
		return empMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Emp> listEmp() {
		return empMapper.listEmp();
	}
}
