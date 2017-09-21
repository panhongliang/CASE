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
        PriorityQueue queue=new PriorityQueue();
        queue.add(5);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
