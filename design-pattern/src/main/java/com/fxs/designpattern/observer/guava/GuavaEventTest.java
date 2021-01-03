package com.fxs.designpattern.observer.guava;

import com.google.common.eventbus.EventBus;

public class GuavaEventTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("test1111");

        //struct到spring mvc的升级 struct面向的是类，spring mvc面向的是方法

        //gperadvice 、event面向的是类，guva面向的是方法
    }
}
