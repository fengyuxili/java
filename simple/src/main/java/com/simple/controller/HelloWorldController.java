package com.simple.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.simple.entity.Emp;
import com.simple.service.EmpService;

@Controller
public class HelloWorldController {
	private static Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Resource
	private EmpService empService;
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello() {
		log.info("=========hello world=============");
		return "hello";
	}
	@ResponseBody
	@RequestMapping(value="/getEmpList", method=RequestMethod.GET)
	public List<Emp> getEmpList() {
		//分页
		Page<Emp> page = PageHelper.startPage(1, 2);
		List<Emp> empList = empService.listEmp();
		log.info("total="+page.getTotal());
		return empList;
	}
}
