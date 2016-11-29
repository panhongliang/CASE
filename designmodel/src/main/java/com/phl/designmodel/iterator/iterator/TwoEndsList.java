package com.phl.designmodel.iterator.iterator;

import java.util.*;

/**
 * Created by panhongliang on 16/1/12.
 * 在集合的左端加入一个元素，再在集合的右端加入一个元素，依次类推
 * direction指定首个元素加入的方向
 */
public class TwoEndsList<E> extends AbstractCollection<E> {

    private static final int LEFT=0;
    private static final int RIGHT=1;
    private int direction=LEFT;//next insert direction
    private ArrayList<E> leftList=new ArrayList<E>();
    private ArrayList<E> rightList=new ArrayList<E>();

    public boolean add(E e) {
        if(direction==LEFT){
            leftList.add(e);
            direction=RIGHT;
        }
        else{
            rightList.add(e);
            direction=LEFT;
        }
        return true;
    }

    public TwoEndsList(){

    }

    public TwoEndsList(int direction){
        this.direction=direction;
    }
    private class Itr implements Iterator<E> {
        int leftCursor=leftList.size()>=1?leftList.size()-1:0;
        int rightCursor=0;
        int currentDirection=LEFT;
        public boolean hasNext() {
           if(currentDirection==LEFT){
               return leftCursor!=-1;
           }else {
               return rightCursor!=rightList.size();
           }
        }
        @SuppressWarnings("unchecked")
        public E next() {
            if(currentDirection==LEFT){
                E e=leftList.get(leftCursor);
                leftCursor=leftCursor-1;
                if(leftCursor==-1)
                    currentDirection=RIGHT;
                return e ;
            }
            if(currentDirection==RIGHT){
                E e= rightList.get(rightCursor);
                rightCursor=rightCursor+1;
                return e;
            }
           return null;
        }

        public void remove() {

        }
    }

    @Override
    public boolean remove(Object o) {

       Iterator it =rightList.iterator();
       while (it.hasNext()){
           Object n=it.next();
           if(o==null && n==null){
               it.remove();
               return true;
           }
           if(n.equals(o)){
               it.remove();
               return true;
           }
       }

        it =leftList.iterator();
        while (it.hasNext()){
            Object n=it.next();
            if(o==null && n==null){
                it.remove();
                return true;
            }
            if(n.equals(o)){
                it.remove();
                return true;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public int size() {
        return leftList.size()+rightList.size();
    }

    @Override
    public boolean contains(Object o) {
        return rightList.contains(o)?true:(leftList.contains(o)?true:false);
    }

    @Override
    public boolean isEmpty() {
        return rightList.isEmpty()&& leftList.isEmpty();
    }
}
