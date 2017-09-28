package com.phl.threadpool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Title:SubmitExecuteMain
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/26 0026 10:34
 */
public class SubmitExecuteMain {
    public static void main(String[] args) throws Exception{
        submit();// FutureTask try catch()
        submitWithGet();
        execute();
    }
    private static void submitWithGet() throws Exception{
        ExecutorService service= Executors.newSingleThreadExecutor();
        Future future=service.submit(new Runnable() {
            @Override
            public void run() {
                int i=7/0;
            }
        });

         future.get();

        service.shutdown();
    }
    private static void submit(){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.submit(new Runnable() {
            @Override
            public void run() {
                int i=7/0;
            }
        });

        service.shutdown();
    }
    private static void execute(){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                int i=7/0;
            }
        });
        service.shutdown();
    }
}
