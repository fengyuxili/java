package com.fxs.designpattern.adapter.loginadapter.v2.adapters;

import com.fxs.designpattern.adapter.loginadapter.ResultMsg;


public class RegistForQQAdapter implements RegistAdapter,LoginAdapter {
    public boolean support(Object adapter) {
        return false;
    }

    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
