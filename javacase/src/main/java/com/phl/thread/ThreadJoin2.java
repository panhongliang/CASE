package com.phl.thread;

/**
 * Created by panhongliang on 16/3/15.
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        T t1=new T("ONE");
        T t2=new T("TWO");
        t1.start();
        t2.start();
        t2.join();
        System.out.println(" main thread over" );
    }

    static class T extends  Thread{
        String name;
        public T(String name){
            this.name=name;
        }
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                if("ONE".equals(name)){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(name+"i = " + i);
            }
        }
    }
}
