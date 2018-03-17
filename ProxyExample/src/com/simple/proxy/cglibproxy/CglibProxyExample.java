package com.simple.proxy.cglibproxy;

import java.lang.reflect.Method;

import com.simple.proxy.target.HelloWorldImpl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * cglig动态代理
 * <p>cglig包的底层是通过字节码处理框架ASM，继承被代理类，并转换字节码并生成新的类。</p>
 * @author sam.feng
 *
 */
public class CglibProxyExample implements MethodInterceptor{
	/**
	 * 生成cglib代理对象
	 * @param clazz 被代理类
	 * @return clazz类的cglib代理对象
	 */
	public Object getProxy(Class clazz) {
		//创建cglib enhancer增强类对象
		Enhancer enhancer = new Enhancer();
		//设置增强类型
		enhancer.setSuperclass(clazz);
		//定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
		enhancer.setCallback(this);
		//生成并返回代理对象
		return enhancer.create();
	}
	/**
	 * @param proxy 代理对象
	 * @param method 当前调度方法
	 * @param args 当前方法参数
	 * @param methodProxy 方法代理
	 * @return 代理结果返回
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("调度真实对象之前的业务处理逻辑...");
		//方法调度，参数1:代理对象，参数2:真实对象方法的参数
		//相当于调度真实对象的方法
		Object obj = methodProxy.invokeSuper(proxy, args);
		System.out.println("调度真实对象之后的业务处理逻辑...");
		return obj;
	}

	public static void main(String[] args) {
		CglibProxyExample cglibProxyExample = new CglibProxyExample();
		HelloWorldImpl proxy = (HelloWorldImpl) cglibProxyExample.getProxy(HelloWorldImpl.class);
		proxy.sayHello();
	}
}
