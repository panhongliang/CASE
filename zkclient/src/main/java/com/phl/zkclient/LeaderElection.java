package com.phl.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017-02-20.
 */
public class LeaderElection {
    private static final String leader="/leader";
    private static final String clients="/clients";
    private static final String servers="127.0.0.1:2181";
    private static final List<LeaderChangeLister> listers=new CopyOnWriteArrayList<LeaderChangeLister>();

    private String clientId;//当前客户端id
    private String leaderId;//选举选出的leader
    private  ZkClient zkClient;

    private void initClientId(){
        clientId= "client_"+UUID.randomUUID().toString();
        System.out.println("clientId:"+clientId);
    }
    private void connZk(){
        zkClient=new ZkClient(servers,1000);
    }
    public LeaderElection(){
        this.initClientId();
        this.connZk();
        this.registerZkListener();
        this.registerZkClient();
    }
   public void registerLeaderChangerListener(LeaderChangeLister lister){
       listers.add(lister);
   }
   public void removeLeaderChangerListener(LeaderChangeLister lister){
       listers.remove(lister);
   }
   private void fireLeaderChangerListener(LeaderEvent event){
       for(LeaderChangeLister lister:listers){
           lister.onLeaderChang(event);
       }
   }
    private  void registerZkListener(){
        this.zkClient.subscribeDataChanges(leader,new IZkDataListener(){
            public void handleDataChange(String dataPath, Object data) throws Exception {
                String newleaderClientId=data==null?null:data.toString();
                LeaderEvent event=new LeaderEvent(clientId);
                event.setLeaderId(newleaderClientId);
                if(data==null){
                    leaderId=null;
                    electLeader();
                }

                //我被选为leader
                else if(newleaderClientId.equals(clientId)){
                    //判断以前是不是leader
                    if(leaderId==null || !leaderId.equals(newleaderClientId)){
                        event.setLeader(true);
                        fireLeaderChangerListener(event);
                    }
                }
                //我没有被选为leader,我为follower
                else if(!newleaderClientId.equals(clientId)){
                        event.setLeader(false);
                        fireLeaderChangerListener(event);
                }
                leaderId=newleaderClientId;
            }
            public void handleDataDeleted(String dataPath) throws Exception {
                leaderId=null;
                electLeader();
            }
        });

    }
    public  void electLeader() {
        boolean leaderExist=this.zkClient.exists(leader);
        if(leaderExist){
            leaderExistCheck();
        }else{
            try{
                this.zkClient.createEphemeral(leader, clientId);
                leaderExistCheck( );
            }catch (ZkNodeExistsException e){
                 leaderExistCheck( );
            }

        }
    }

    private void registerZkClient(){
        if(!this.zkClient.exists(clients)){
            try {
                this.zkClient.createPersistent(clients);
            }catch (ZkNodeExistsException e){
            }
        }
        if(!this.zkClient.exists(clients+"/"+clientId)){
            try {
                this.zkClient.createEphemeral(clients+"/"+clientId, clients+"/"+clientId);
            }catch (ZkNodeExistsException e){
            }
        }

    }
    private void leaderExistCheck(){
        try {
            Object data=this.zkClient.readData(leader);
            if(data==null){
                electLeader();
                return ;
            }
            leaderId= data.toString();//
            LeaderEvent event=new LeaderEvent(clientId);
            event.setLeaderId(leaderId);
            if(leaderId.equals(clientId)){
                event.setLeader(true);
            }else {
                event.setLeader(false);
            }
            fireLeaderChangerListener(event);
        }catch (ZkNoNodeException e){
            electLeader();
        }
    }
    public void keepClientAlive(){
        while (true){
            try {
                sleep(5000);
            } catch (InterruptedException e) {

            }
        }
    }

}
