package com.fxs.designpattern.observer.event.mouseevent;

import com.fxs.designpattern.observer.event.core.EventListener;

public class Mouse extends EventListener {
    public void click() {
        System.out.println("调用单击方法");
        this.trigger(MouseEventType.ON_CLICK);
    }
    public void move() {
        System.out.println("调用移动方法");
        this.trigger(MouseEventType.ON_MOVE);
    }
}
