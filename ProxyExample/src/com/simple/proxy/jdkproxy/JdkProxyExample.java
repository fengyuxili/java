package com.simple.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.simple.proxy.target.HelloWorld;
import com.simple.proxy.target.HelloWorldImpl;
/**
 * JDK动态代理
 * <p>JDK动态代理需要实现InvocationHandler接口，被代理类必须实现一个或多个接口</p>
 * <p>动态代理其实就是通过反射，在newProxyInstance时用传入的接口在内存中生成了一个新的类，
 * 这个类在调用sayHello()方法的时候，
 * 其实调用的是代理类的invoke 方法，
 * 而那个invoke就会拦截真正的方法调用，
 * 这样就可以在真正的方法调用前后添加需要的服务
 * </p>
 * @author sam.feng
 *
 */
public class JdkProxyExample implements InvocationHandler{

	//真实对象
	private Object target = null;
	/**
	 * 建立代理对象和真实对象的代理关系，并返回代理对象
	 * @param target 真实对象
	 * @return 代理对象
	 */
	public Object bind(Object target) {
		this.target = target;
		//newProxyInstance参数
		//1.真实对象类加载器
		//2.真实对象实现的接口，需要用传入的接口在内存中生成了一个新的类
		//3.代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}
	
	/**
	 * @param proxy 代理对象
	 * @param method 当前调度方法
	 * @param args 当前方法参数
	 * @return 代理结果返回
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("调度真实对象之前的业务处理逻辑...");
		//方法调度，参数1:代理对象，参数2:真实对象方法的参数
		//相当于调度真实对象的方法
		Object obj = method.invoke(target, args);
		System.out.println("调度真实对象之后的业务处理逻辑...");
		return obj;
	}

	public static void main(String[] args) {
		JdkProxyExample jdkProxyExample = new JdkProxyExample();
		//绑定代理对象和真实对象的关系，
		HelloWorld proxy = (HelloWorld)jdkProxyExample.bind(new HelloWorldImpl());
		//调用真实对象方法，内存中生成了一个新的类，
		//这个类在调用sayHello() 其实调用的是invoke方法， 而那个invoke就会拦截真正的方法调用
		proxy.sayHello();
	}
}
