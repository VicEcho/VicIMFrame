package com.thread;

/**
 * @author Vic Zhang
 * @date 2019/5/29 3:42 PM
 */
public class MyThread2 implements Runnable {

    private int count = 10;

    @Override
    public synchronized void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count ==== " + count);
    }

    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        for(int i = 0; i < 5; i++) {
            new Thread(thread2, "thread" + i).start();
        }
    }



}
