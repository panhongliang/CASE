package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 *
 * 员工
 */
public class Client {


    public Client() {
    }
    public PurchaseRequest sendRequest(int type, int number, float price){
        return new PurchaseRequest( type,  number,  price);
    }
}
