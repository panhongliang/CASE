package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 *
 * 请求处理者
 */
public abstract class Approver {
    Approver successor;

    String name;

    public Approver(String name){
        this.name=name;
    }

    public  void setSuccessor(Approver successor){
        this.successor=successor;
    }

    public abstract void handerRequest(PurchaseRequest r);
}
