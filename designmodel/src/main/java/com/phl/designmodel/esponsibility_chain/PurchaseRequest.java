package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 * 采购请求
 */
public class PurchaseRequest {
    private int type;
    private int number;
    private float price;
    private int id;


    public PurchaseRequest(int type, int number, float price) {
        this.type = type;
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "type=" + type +
                ", number=" + number +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    public int getType() {
        return type;
    }

    public float getSum(){
        return number*price;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return (int)(Math.random()*1000);
    }

    public void setId(int id) {
        this.id = id;
    }
}
