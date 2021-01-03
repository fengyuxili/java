package com.fxs.designpattern.decorator.cake;

import java.io.FileInputStream;

public class CakeTest {

    public static void main(String[] args) {
        Cake cake = new BaseCake();
        cake = new EggDecorator(cake);
        cake = new EggDecorator(cake);
        cake = new SausageDecorator(cake);
        System.out.println(cake.getMsg()+"，价格"+cake.getPrice());
    }
}
