package com.fxs.designpattern.decorator.cake;

public abstract class CakeDecorator extends Cake{

    private Cake cake;

    public CakeDecorator(Cake cake) {
        this.cake = cake;
    }

    @Override
    protected String getMsg() {
        return this.cake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.cake.getPrice();
    }
}
