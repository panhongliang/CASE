package com.phl.zk;

/**
 * Created by panhongliang on 16/3/2.
 */

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class SelfWatcher implements Watcher {

    ZooKeeper zk = null;


    public void process(WatchedEvent event) {
        System.out.println(event.toString());
    }

    SelfWatcher(String address) {
        try {
            zk = new ZooKeeper(address, 3000, this);     //在创建ZooKeeper时第三个参数负责设置该类的默认构造函数
            zk.create("/test2", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (IOException e) {
            e.printStackTrace();
            zk = null;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setWatcher() {
        try {
            Stat s = zk.exists("/test2", true);
            if (s != null) {
                zk.getData("/test2", false, s);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void trigeWatcher() {
        try {
            Stat s = zk.exists("/test2", false);        //此处不设置watcher
            zk.setData("/test2", "a".getBytes(), s.getVersion());//修改数据时需要提供version，version设为-1表示强制修改
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        if (zk != null)
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        SelfWatcher inst = new SelfWatcher("127.0.0.1:2181");
        inst.setWatcher();
        inst.trigeWatcher();
        inst.disconnect();
    }

}
