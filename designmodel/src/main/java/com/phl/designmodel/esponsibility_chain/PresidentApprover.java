package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 */
public class PresidentApprover extends  Approver {
    public void handerRequest(PurchaseRequest r) {
        if( r.getSum()>=10000){
            System.out.println(this.name+" handler request " +r);
        }else {
            successor.handerRequest(r);
        }
    }

    public PresidentApprover(String name) {
        super(name+" president");
    }
}
