package com.phl.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by panhongliang on 16/1/17.
 */
public class ReentrantReadWriteLockTest {
    static Map<String,Object> map=new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    static Lock r=rwl.readLock();
    static Lock w=rwl.writeLock();
    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            r.unlock();
        }
        return null;
    }

    public static final Object put(String key,Object value){
        w.lock();
        try {
            return map.put(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            w.unlock();
        }
        return null;
    }
    public static void main(String[] args) {

    }

}
