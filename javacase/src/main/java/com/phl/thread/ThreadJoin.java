package com.phl.thread;

/**
 * Created by panhongliang on 16/1/22.
 *
 * a.join 等待a线程执行结束后，父线程再执行。
 * 父线程对子线程的等待
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        FatherThread t=new FatherThread("father");
        t.start();
        System.out.println("main thread");//main thread不会等待
    }

}
class FatherThread extends Thread {

    public FatherThread(String s){
        super(s);
    }
    @Override
    public void run() {
        final  Thread t=Thread.currentThread();
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" print "+i);
            if(i==0){
                final SonThread sun=new SonThread("son thread");
                sun.start();
                try {
                    sun.join(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class SonThread extends Thread {

    public SonThread(String name){
        super(name);
    }
    @Override
    public void run() {

        while (true){

            for(int i=0;i<100;i++){  try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }

    }
}

