package com.fxs.designpattern.observer.event.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListener{
    //JDK也是这样设计
    private Map<String,Event> events = new HashMap<>();

    public void addListener(String eventType, Object target) {
        try {
            this.addListener(eventType,
                    target,
                    target.getClass().getMethod("on"+toUpperFirstCase(eventType), Event.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addListener(String eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType, new Event(target, callback));
    }

    protected void trigger(Event event) {

        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            if (event.getCallback() != null) {
                event.getCallback().invoke(event.getTarget(), event);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void trigger(String trigger) {
        if (!this.events.containsKey(trigger)) {
            return;
        }
        Event event = this.events.get(trigger);
        event.setTrigger(trigger);
        trigger(event);
    }

    private String toUpperFirstCase(String str) {
        if (str == null) {
            return "";
        }
        char[] chars = str.toCharArray();
        chars[0] -=32;
        return String.valueOf(chars);
     }
}
