package com.fxs.designpattern.adapter.power;

public class PowerAdapterTest {

    public static void main(String[] args) {
        new PowerAdapter(new AC220()).ouputDC5V();
    }
}
