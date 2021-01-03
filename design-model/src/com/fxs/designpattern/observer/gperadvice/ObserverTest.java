package com.fxs.designpattern.observer.gperadvice;

public class ObserverTest {
    public static void main(String[] args) {
        GPer gper = GPer.getInstance();
        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");
        Question question = new Question();
        question.setUsername("小米");
        question.setContent("JAVA 观察者模式原理");
        //添加观察者
        gper.addObserver(tom);
        gper.addObserver(mic);

        gper.publishQuestion(question);

    }
}
