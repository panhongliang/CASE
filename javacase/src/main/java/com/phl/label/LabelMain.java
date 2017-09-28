package com.phl.label;

/**
 * @Title:LabelMain
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/26 0026 10:29
 */
public class LabelMain {
    public static void main(String[] args) {
        new LabelMain().run();
    }

    private void run(){
        b1:{
            for(int i=0;i<10;i++){
                b2:{
                    for(int k=0;k<10;k++){
                        if(k==5){
                            break b2;
                        }
                            System.out.println("========i="+i+",k="+k);

                    }
                }
            }
        }
    }
}
