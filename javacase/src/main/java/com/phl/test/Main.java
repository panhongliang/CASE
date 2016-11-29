package com.phl.test;

/**
 * Created by panhongliang on 15/11/4.
 */
public class Main {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
        System.out.println(" RUNNING-1:" + to32(Integer.toBinaryString(-1 << COUNT_BITS)));
        System.out.println(" SHUTDOWN0:" + to32(Integer.toBinaryString(0 << COUNT_BITS)));
        System.out.println("STOP     1:" + to32(Integer.toBinaryString(1 << COUNT_BITS)));
        System.out.println("TIDYING   :" + to32(Integer.toBinaryString(2 << COUNT_BITS)));
        System.out.println("TERMINATED:" + to32(Integer.toBinaryString(3 << COUNT_BITS)));
        System.out.println("CAPACITY  :" + to32(Integer.toBinaryString(CAPACITY)));
         System.out.println("~CAPACITY:" + to32(Integer.toBinaryString(~CAPACITY)));
        String l=" RUNNING:  Accept new tasks and process queued tasks\n" +
                "     *   SHUTDOWN: Don't accept new tasks, but process queued tasks\n" +
                "     *   STOP:     Don't accept new tasks, don't process queued tasks,\n" +
                "     *             and interrupt in-progress tasks\n" +
                "     *   TIDYING:  All tasks have terminated, workerCount is zero,\n" +
                "     *             the thread transitioning to state TIDYING\n" +
                "     *             will run the terminated() hook method\n" +
                "     *   TERMINATED: terminated() has completed";

        System.out.println(l);

    }
    public static String to32(String str){
        if(str.length()==32){
            return str;
        }
        int len=str.length();
        int rest=32-len;
        String s="";
        for(int i=0;i<rest;i++){
            s+="0";
        }
        return s+str;
    }
}
