package com.fxs.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableZuulProxy
public class ZuulRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulRouterApplication.class, args);
	}

}
