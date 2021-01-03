package com.fxs.designpattern.observer.guava;

import com.google.common.eventbus.Subscribe;

public class GuavaEvent {

    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行Subscribe方法,参数"+str);
    }
}
