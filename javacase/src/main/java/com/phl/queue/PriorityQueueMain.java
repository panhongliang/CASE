package com.phl.queue;

import java.util.PriorityQueue;

/**
 * @Title:PriorityQueueMain
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/20 0020 13:28
 */
public class PriorityQueueMain {
    public static void main(String[] args) {
        new PriorityQueueMain().p2();
    }
    private void p3(){
        PriorityQueue queue=new PriorityQueue();
        Obj obj=new Obj(1);
        queue.add(obj);
        queue.add(obj);
        queue.add(obj);
        queue.add(obj);
        System.out.println(queue.size());
    }

    private void p2(){
        PriorityQueue queue=new PriorityQueue();
        Obj obj=new Obj(1);
        queue.add(obj);
        Obj obj1=new Obj(4);
        queue.add(obj1);
        Obj obj2=new Obj(3);
        queue.add(obj2);
        Obj obj3=new Obj(8);
        queue.add(obj3);
        Obj obj4=new Obj(0);
        queue.add(obj4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
    private void p(){
        PriorityQueue queue=new PriorityQueue();
        queue.add(5);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
