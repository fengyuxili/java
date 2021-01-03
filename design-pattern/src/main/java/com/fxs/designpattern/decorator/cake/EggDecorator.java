package com.fxs.designpattern.decorator.cake;

public class EggDecorator extends CakeDecorator {
    public EggDecorator(Cake cake) {
        super(cake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() +"，加1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
