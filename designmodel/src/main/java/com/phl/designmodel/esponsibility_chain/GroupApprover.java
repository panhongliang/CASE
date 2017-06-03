package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 *
 * 组长
 */
public class GroupApprover extends Approver {
    public void handerRequest(PurchaseRequest r) {
        if(r.getSum()<5000){
            System.out.println(this.name+" handler request " +r);
        }else {
            successor.handerRequest(r);
        }
    }

    public GroupApprover(String name) {
        super(name+" GroupLeader");
    }
}
