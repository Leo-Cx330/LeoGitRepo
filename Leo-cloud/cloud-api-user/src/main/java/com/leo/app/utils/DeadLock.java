package com.leo.app.utils;

public class DeadLock implements Runnable {

    private static  Object a=new Object();
    private static  Object b=new Object();
    private boolean falg;


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
        if(falg){
            synchronized (a){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println(threadName + "已经进入同步代码块objectB");
                }
            }
        }else {
            synchronized (b){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println(threadName + "已经进入同步代码块objectA");
                }
            }
        }
    }



    public static void main(String[] args) {
        DeadLock t1=new DeadLock();
        DeadLock t2=new DeadLock();
        t1.falg=true;
        t2.falg=false;
        Thread thread1=new Thread(t1);
        Thread thread2=new Thread(t2);
        thread1.start();
        thread2.start();
    }

}
