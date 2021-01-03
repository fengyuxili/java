package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean;
	@Test
	public void contextLoads() {
		ExecutorService object = threadPoolExecutorFactoryBean.getObject();
		object.submit(new TreadDemo());
	}


}
