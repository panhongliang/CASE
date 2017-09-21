package com.phl.opentsdb;

import com.phl.opentsdb.query.QueryDataUtil;
import net.opentsdb.core.TSQuery;
import net.opentsdb.core.TSSubQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * @Title:Main
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/8/11 0011 14:04
 */
public class Main {
    static String metricName="AB";
    public static void main(String[] args) throws Exception {
        add();
    }
    private static void query(){

    }
    private static long getTime(int s){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(format.parse("2013-01-01 08:00:00"));
            calendar.set(Calendar.SECOND,s);
            return calendar.getTime().getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1L;
    }

    private static void add() throws Exception {
        //host=webserver01, cpu=0
        //rowkey:
        //metric_uid timestamp tagk_uid tagv_uid
        //metric-->3字节整数、tagk-->3字节整数、tagv-->3字节
        final Random random=new Random();
        final finalClass finalClass=new finalClass();
        ExecutorService service=Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            finalClass.setI(i);
            service.submit(new Runnable() {
                public void run() {
                    for(int k=0;;k++){
                        try {
                            sleep(1000);
                            long t=new Date().getTime();
                            List<TSDBDataPoint> datas=new ArrayList<TSDBDataPoint>();
                            Map<String, String> tags=new HashMap<String, String>();

                            tags.put("cpu",finalClass.getI()+"");
                            TSDBDataPoint point=new TSDBDataPoint(new Date().getTime(),random.nextInt(5),tags);
                            datas.add(point);

                            AddDataUtil.addData(metricName,datas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
        service.shutdown();
    }
    static class finalClass{
        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

}
