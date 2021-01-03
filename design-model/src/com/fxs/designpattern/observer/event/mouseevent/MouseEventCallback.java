package com.fxs.designpattern.observer.event.mouseevent;

import com.fxs.designpattern.observer.event.core.Event;

public class MouseEventCallback {

    public void onClick(Event e) {
        System.out.println("鼠标点击事件:"+e.toString());
    }
}
