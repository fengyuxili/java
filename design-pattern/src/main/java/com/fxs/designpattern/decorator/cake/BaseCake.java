package com.fxs.designpattern.decorator.cake;

/**
 * 基础套餐
 */
public  class BaseCake extends Cake{

    protected  String getMsg() {
        return  "蛋糕";
    }
    protected  int getPrice(){
        return 5;
    }
}
