package com.phl.thread;

/**
 * Created by panhongliang on 16/1/22.
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
        LocalThread one= new LocalThread("T1");
        one.start();

        LocalThread two= new LocalThread("T2");
        two.start();
    }
}

class LocalThread extends Thread{


    public LocalThread(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        ThreadLocalMap.set(getName());
        doA();
        doB();
        doC();
    }
    private void doA(){
        String i= (String)ThreadLocalMap.get();
        System.out.println("thread in do a:"+getName()+" "+i);
    }
    private void doB(){
        String i= (String)ThreadLocalMap.get();
        System.out.println("thread in do b:"+getName()+" "+i);
    }
    private void doC(){
        String i= (String)ThreadLocalMap.get();
        System.out.println("thread in do c:"+getName()+" "+i);
    }

}
