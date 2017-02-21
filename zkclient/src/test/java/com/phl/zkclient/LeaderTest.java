package com.phl.zkclient;

/**
 * Created by Administrator on 2017-02-21.
 */
public class LeaderTest implements LeaderChangeLister {

    public void onLeaderChang(LeaderEvent event) {
        if(event.isLeader()){
            System.out.println("i am a leader,clientId:"+event.getSource());
        }else{
            System.out.println("i am a follower:clientId:"+event.getSource());
        }
    }

    public static void main(String[] args) {
        LeaderTest test=new LeaderTest();
        LeaderElection election=new LeaderElection();
        election.registerLeaderChangerListener(test);
        election.electLeader();
        election.keepClientAlive();
    }
}
