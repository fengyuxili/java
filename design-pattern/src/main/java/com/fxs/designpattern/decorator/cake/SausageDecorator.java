package com.fxs.designpattern.decorator.cake;

public class SausageDecorator extends CakeDecorator {
    public SausageDecorator(Cake cake) {
        super(cake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() +"，加1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
