package com.thread;

/**
 * @author Vic Zhang
 * @date 2019/5/29 3:16 PM
 */
public class Test01 {

    private int count = 10;

    private static int count2 = 10;

    private Object obj = new Object();

    public void lock() {
        synchronized (obj) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count === " + count);
        }
    }
    public synchronized static void lock3() { // 静态方法里相当于锁定Test01.Class
        count2--;
        System.out.println(Thread.currentThread().getName() + "count2 === " + count2);
    }

    public synchronized void lock2() { // 等同于 synchronized(this)  锁定this对象
        count--;
        System.out.println(Thread.currentThread().getName() + "count === " + count);
    }


}
