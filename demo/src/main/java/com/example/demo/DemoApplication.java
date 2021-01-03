package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean() {
		ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
		threadPoolExecutorFactoryBean.setCorePoolSize(1);
		threadPoolExecutorFactoryBean.setMaxPoolSize(1);

		return threadPoolExecutorFactoryBean;
	}
}
