package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 *
 * 部长
 */
public class DepartmentApprover extends Approver {
    public void handerRequest(PurchaseRequest r) {
        if(r.getSum()>=5000 && r.getSum()<10000){
            System.out.println(this.name+" handler request " +r);
        }else {
            successor.handerRequest(r);
        }
    }

    public DepartmentApprover(String name) {
        super(name+" department approver");
    }
}
