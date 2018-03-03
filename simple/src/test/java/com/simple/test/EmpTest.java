package com.simple.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.simple.entity.Emp;
import com.simple.service.EmpService;
import com.simple.test.base.BaseTest;

public class EmpTest extends BaseTest{

	@Resource
	private EmpService empService;
	@Test
	public void getEmp() {
		Emp emp = empService.selectByPrimaryKey(1);
		System.out.println(JSON.toJSONString(emp));
	}
}
