package com.fxs.designpattern.observer.gperadvice;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class Teacher implements Observer {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GPer gper = (GPer) o;
        Question question = (Question)arg;
        System.out.println("==========");
        System.out.println(name + "老师您好！" +
        "您收到了"+gper.getName()+question.getUsername()+"提的问题:"+question.getContent()
        );
    }

}
