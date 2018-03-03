package com.simple.mapper;

import java.util.List;

import com.simple.entity.Emp;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

	List<Emp> listEmp();
}