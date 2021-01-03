package com.fxs.designpattern.adapter.loginadapter.v2.adapters;

import com.fxs.designpattern.adapter.loginadapter.ResultMsg;

/**
 *
 */
public class LoginForQQAdapter implements LoginAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
