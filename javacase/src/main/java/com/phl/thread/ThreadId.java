package com.phl.thread;

import java.util.concurrent.atomic.AtomicInteger;


public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

         // Thread local variable containing each thread's ID
         private static final ThreadLocal<Integer> threadId =
             new ThreadLocal<Integer>() {
             protected Integer initialValue() {
                     return nextId.getAndIncrement();
             }
        };

         // Returns the current thread's unique ID, assigning it if necessary
         public static int get() {
             return threadId.get();
         }
}
