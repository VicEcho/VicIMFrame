package com.thread;

/**
 * 脏读问题 ：copyOnWrite
 * synchronized  加锁  出现异常时，虚拟机自动释放锁
 * volatile
 *
 *
 */

/**
 * @author Vic Zhang
 * @date 2019/5/29 2:58 PM
 */

public class MyThread01 {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
