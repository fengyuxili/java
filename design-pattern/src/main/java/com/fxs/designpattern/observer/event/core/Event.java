package com.fxs.designpattern.observer.event.core;

import java.lang.reflect.Method;

public class Event {
    //事件源，发起者
    private  Object source;
    //事件触发通知目标
    private  Object target;
    //事件触发回调
    private Method callback;
    //事件触发名称
    private  String trigger;
    //事件触发名称
    private  Long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ",\n target=" + target +
                ",\n callback=" + callback +
                ",\n trigger='" + trigger + '\'' +
                ",\n time=" + time +
                '}';
    }
}
