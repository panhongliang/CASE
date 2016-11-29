package com.phl.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by panhongliang on 15/11/2.
 */
public class Main {


    public static void main(String[] args) {
        final LongTimeTask task=new LongTimeTask();
        final ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(5,7,5L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1));
        ScheduledExecutorService scheduledService=Executors.newSingleThreadScheduledExecutor();
        scheduledService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                int active=threadPoolExecutor.getActiveCount();
                long completed= threadPoolExecutor.getCompletedTaskCount();
                int idle=threadPoolExecutor.getMaximumPoolSize()-active;
                System.out.printf("active count:"+active+",completed:"+completed+",idle:"+idle+"\n");
            }
        },1,1,TimeUnit.SECONDS);
        List<Future<Integer>> futures=new ArrayList<Future<Integer>>();
        final TaskId taskId=new TaskId();
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                taskId.setId(i);
                Future<Integer> future=threadPoolExecutor.submit(new Callable<Integer>() {
                    public Integer call() {
                        return task.doTask(taskId);
                    }
                });
                while (!future.isDone()){
                    future.get(900L,TimeUnit.MILLISECONDS);

                }
            }catch (RejectedExecutionException e){
                System.out.printf("系统繁忙"+"\n");
            }catch (TimeoutException ee){
                System.out.printf("服务超时"+taskId.getId()+"\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.printf("执行异常"+"\n");
            }

        }
    }
}
class TaskId{
    private int id;
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
}
