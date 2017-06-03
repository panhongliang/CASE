package com.phl.designmodel.esponsibility_chain;

/**
 * Created by Administrator on 2017/6/3.
 */
public class Main {
    public static void main(String[] args) {
        Client client=new Client();

        //请求处理者
        Approver groupLeader=new GroupApprover("tom");
        Approver department=new DepartmentApprover("jerry");
        Approver vicePresident=new VicePresidentApprover("kate");
        Approver president=new PresidentApprover("bush");

        //责任链
        //谁在链的前面或后面没有关系，因为每个处理者只处理满足自己条件的请求，实现解耦
        groupLeader.setSuccessor(department);
        department.setSuccessor(vicePresident);
        vicePresident.setSuccessor(president);
        president.setSuccessor(groupLeader);

        //谁先处理请求不会影响结果，，因为每个处理者只处理满足自己条件的请求  实现解耦
        groupLeader.handerRequest(client.sendRequest(1,300,110.0f));
        president.handerRequest(client.sendRequest(1,300,110.0f));//与 groupLeader.handerRequest(client.sendRequest(1,300,110.0f));结果一样
    }
}
