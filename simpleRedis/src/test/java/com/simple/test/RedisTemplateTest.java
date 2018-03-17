package com.simple.test;

import java.io.Serializable;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alibaba.fastjson.JSON;
import com.simple.entity.Emp;
import com.simple.test.base.BaseTest;

public class RedisTemplateTest extends BaseTest{
	
	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Test
	public void testRedis() {
//		Emp emp = new Emp();
//		emp.setId(1);
//		emp.setDeptId(2);
//		emp.setName("张三");
//		ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
//		valueOperations.set("emp_1", emp);
		Emp empFromRedis = (Emp) redisTemplate.opsForValue().get("emp_1");
		System.out.println(JSON.toJSONString(empFromRedis));
	}
}
