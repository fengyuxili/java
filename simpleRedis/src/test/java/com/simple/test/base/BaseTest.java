package com.simple.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath*:spring/applicationContext.xml",
	"classpath*:spring/applicationContext-mybatis.xml",
	"classpath*:spring/springmvc-config.xml", 
	"classpath*:spring/applicationContext-redis.xml"
})
public abstract class BaseTest {

}
