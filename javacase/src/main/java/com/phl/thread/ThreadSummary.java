package com.phl.thread;

/**
 * Created by panhongliang on 16/1/22.
 */
public class ThreadSummary {
    /**
     * 线程的状态可以分为4+3
     * 4： new->runnable->running->terminal
     * 3:  lock pool wait:在对象锁上的等待，需要获取对象的锁，才能进行runnable
     *     wait pool wait:在对象wait方法上的等待,需要等待别的线程在该对象上的notify，notifyAll
     *     other wait:其它等待 如sleep,join
     *
     *
     （一）、等待阻塞：运行的线程执行wait()方法，JVM会把该线程放入等待池中。
     （二）、同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
     （三）、其他阻塞：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
     */
}
