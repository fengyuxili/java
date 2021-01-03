package com.fxs.designpattern.observer.event.mouseevent;

import com.fxs.designpattern.observer.event.core.EventListener;

public class MouseEventTest {
    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();
        Mouse mouse = new Mouse();
        //@ 回调方法
        mouse.addListener(MouseEventType.ON_CLICK, callback);
        mouse.click();
    }
}
