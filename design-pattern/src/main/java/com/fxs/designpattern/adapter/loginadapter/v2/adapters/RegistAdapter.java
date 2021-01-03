package com.fxs.designpattern.adapter.loginadapter.v2.adapters;

import com.fxs.designpattern.adapter.loginadapter.ResultMsg;

/**
 *
 */
public interface RegistAdapter {
    boolean support(Object adapter);
    ResultMsg login(String id, Object adapter);
}
