package com.phl.volatilE;

/**
 * Created by panhongliang on 15/10/25.
 */
public class Volatile extends  Thread {

    boolean stop=false;

    public void run() {
        while (true){
            System.out.printf(Boolean.toString(stop)+"\n");
        }
    }
    public void stopMe(){
        stop=true;
    }


    public static void main(String[] args) {
        Volatile v=new Volatile();
        v.start();

        v.stopMe();

    }
}
