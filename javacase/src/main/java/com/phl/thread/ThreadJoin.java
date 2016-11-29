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

    private String s;
    public FatherThread(String s){
        this.s=s;
    }
    @Override
    public void run() {

        for(int i=0;i<10;i++){
            System.out.println(s+" print "+i);
            if(i==0){
                SonThread sun=new SonThread();
                sun.start();
                try {
                    sun.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class SonThread extends Thread {

    @Override
    public void run() {

        for(int i=0;i<100;i++){
            System.out.println(" son print "+i);
        }
    }
}

