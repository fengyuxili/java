package com.fxs.designpattern.adapter.loginadapter.v2.adapters;

import com.fxs.designpattern.adapter.loginadapter.ResultMsg;

/**
 *
 */
public class LoginForWechatAdapter implements LoginAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
