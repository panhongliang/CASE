package com.phl.netty.objectDecoder;

import java.io.Serializable;

/**
 * Created by panhongliang on 16/1/25.
 */
public class SubscribeResponse implements Serializable {

    private int subReqId;
    private int respCode;
    private String desc;

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "desc='" + desc + '\'' +
                ", subReqId=" + subReqId +
                ", respCode=" + respCode +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }
}
