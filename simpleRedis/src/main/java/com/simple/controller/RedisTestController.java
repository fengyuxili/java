package com.simple.controller;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.entity.Emp;
import com.simple.service.EmpService;


@Controller
public class RedisTestController {
	private static Logger LOGGER = LoggerFactory.getLogger(RedisTestController.class);
	
	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	@Resource
	private EmpService empService;
	@ResponseBody
	@RequestMapping(value="/getEmp/{id}", method=RequestMethod.GET)
	public Emp getEmp(@PathVariable Integer id) {
		//Set<Serializable> keys = redisTemplate.keys("*");
		Emp empFromRedis = (Emp) redisTemplate.opsForValue().get("emp_"+id);
		if (null == empFromRedis) {
			Emp emp = empService.selectByPrimaryKey(id);
			ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
			valueOperations.set("emp_"+id, emp);
			LOGGER.info("get emp from DB, id="+id);
			return emp;
		}
		LOGGER.info("get emp from redis, id="+id);
		return empFromRedis;
	}
}
