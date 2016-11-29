package com.phl.designmodel.iterator;

/**
 * Created by panhongliang on 16/1/12.
 */
public class Main {
    public static void main(String[] args) {
        TwoEndsList twoEndsList=new TwoEndsList();
        twoEndsList.add("a");
        twoEndsList.add("b");
        twoEndsList.add("c");
        twoEndsList.add("d");
        twoEndsList.add("e");
        twoEndsList.add("f");
        twoEndsList.add("g");

       /* Iterator it = twoEndsList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("========:"+twoEndsList.size());
        System.out.println("========");
        twoEndsList.remove("d");
         it = twoEndsList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("========:"+twoEndsList.size());


        TwoEndsList twoEndsList0=new TwoEndsList(1);
        twoEndsList0.add("a");
        twoEndsList0.add("b");
        twoEndsList0.add("c");
        twoEndsList0.add("d");
        twoEndsList0.add("e");
        twoEndsList0.add("f");
        twoEndsList0.add("g");
        it = twoEndsList0.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }*/
    }
}
