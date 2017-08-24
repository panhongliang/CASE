package com.phl;

/**
 * @Title:Main
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/8/15 0015 10:49
 */
public class Main
{
    private static final int COUNT_BITS = Integer.SIZE - 3;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
        System.out.println("   RUNNING:"+s32(Integer.toBinaryString(RUNNING)));
        System.out.println("  SHUTDOWN:"+s32(Integer.toBinaryString(SHUTDOWN)));
        System.out.println("      STOP:"+s32(Integer.toBinaryString(STOP)));
        System.out.println("   TIDYING:"+s32(Integer.toBinaryString(TIDYING)));
        System.out.println("TERMINATED:"+s32(Integer.toBinaryString(TERMINATED)));
        System.out.println("  CAPACITY:"+s32(Integer.toBinaryString(CAPACITY)));
        int s=0b11100000000000000000000000011111;
        int r=runStateOf(s);
        System.out.println("         R:"+s32(Integer.toBinaryString(r)));
    }
    public  static  String s32(String str){
        if(str.length()==32)return str;
        int need=32-str.length();
        for(int i=0;i<need;i++){
            str="0"+str;
        }
        return str;
    }
    private static int runStateOf(int c)     { return c & ~CAPACITY; }

}
