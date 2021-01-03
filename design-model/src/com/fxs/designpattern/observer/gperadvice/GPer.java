package com.fxs.designpattern.observer.gperadvice;

import java.util.Observable;

/**
 * 被观察者
 */
public class GPer extends Observable {
    private String name = "GPer生态圈";
    private static final GPer gper = new GPer();

    private GPer() {
    }

    public static GPer getInstance() {
        return gper;
    }
    public String getName() {
        return name;
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUsername() + "在" +this.name + "提交了一个问题");
        setChanged();
        notifyObservers(question);
    }

}
