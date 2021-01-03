package com.fxs.designpattern.singleton;

public class ContainerSingletonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" "+ContainerSingleton.getBean("com.fxs.designmodel.singleton.PoJoTest"));
            }).start();

        }
    }
}
