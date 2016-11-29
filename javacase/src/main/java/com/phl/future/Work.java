package com.phl.future;

/**
 * Created by panhongliang on 15/11/4.
 */
public class Work {
    Runnable firstTask;
    public Work(Runnable firstTask){
        this.firstTask=firstTask;
        new Thread(firstTask).start();
    }

}
