package com.example.demo;

public class TreadDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(i);
        }
    }
}
