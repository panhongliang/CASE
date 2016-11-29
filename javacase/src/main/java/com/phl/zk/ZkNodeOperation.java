package com.phl.zk;

/**
 * Created by panhongliang on 16/3/2.
 */

import org.apache.zookeeper.*;

public class ZkNodeOperation {
    public static void main(String args[])throws Exception{
        String zkServer = "127.0.0.1:2181";

        ZooKeeper zk = new ZooKeeper(zkServer,116000,new Watcher(){

            public void process(WatchedEvent watchedEvent) {
                System.out.println("watchedEvent = " + watchedEvent);
            }
        });
/*
        //创建节点
        System.out.println("创建testDir1节点");
        Stat stat=zk.exists("/testDir1", true);
        if(stat==null){
            zk.create("/testDir1", "testDir1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }


        //创建子节点
        System.out.println("\n创建testDir1节点的子节点");
        zk.create("/testDir1/sub1", "sub1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/testDir1/sub2", "sub2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //取出子节点列表
        System.out.println("\ntestDir1的子节点列表为："+zk.getChildren("/testDir1", true));

        //取出节点的数据
        System.out.println("\n取出/testDir1/sub1节点的数据为：" + new String(zk.getData("/testDir1/sub1", false, null)));

        //修改节点数据
        zk.setData("/testDir1/sub1", "sub3".getBytes(),-1);
        System.out.println("\n取出/testDir1/sub1节点的数据为(修改后)：" + new String(zk.getData("/testDir1/sub1", false, null)));

        //删除节点
        System.out.println("\n删除节点/testDir1/sub1");
        zk.delete("/testDir1/sub1", -1);

        System.out.println("\ntestDir1的子节点列表为：" + zk.getChildren("/testDir1", true));*/
        zk.create("/testDir1/sub1/ephemeral","abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.create("/testDir1/sub1/seq","abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        zk.close();
        while (true){}
    }
}

