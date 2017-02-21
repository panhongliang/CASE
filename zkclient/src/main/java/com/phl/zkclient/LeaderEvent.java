package com.phl.zkclient;

import java.util.EventObject;

/**
 * Created by Administrator on 2017-02-21.
 */
public class LeaderEvent extends EventObject {

    private boolean leader;
    private String leaderId;

    public LeaderEvent(Object  source) {
        super(source);
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }
}
