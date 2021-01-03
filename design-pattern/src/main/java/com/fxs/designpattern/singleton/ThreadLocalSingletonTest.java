package com.fxs.designpattern.singleton;

/**
 * 线程内安全，线程间不安全
 */
public class ThreadLocalSingletonTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 1 "+ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName()+" 2 "+ThreadLocalSingleton.getInstance());
            }).start();

        }
    }
}
