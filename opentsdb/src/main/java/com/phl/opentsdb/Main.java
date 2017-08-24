package com.phl.opentsdb;

import com.phl.opentsdb.query.QueryDataUtil;
import net.opentsdb.core.TSQuery;
import net.opentsdb.core.TSSubQuery;

import java.util.*;

/**
 * @Title:Main
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/8/11 0011 14:04
 */
public class Main {
    static String metricName="sys.cpu.user";
    public static void main(String[] args) throws Exception {
        add();
    }
    private static void query(){

    }
    private static void add() throws Exception {
        //host=webserver01, cpu=0
        //rowkey:
        //metric_uid timestamp tagk_uid tagv_uid
        //metric-->3字节整数、tagk-->3字节整数、tagv-->3字节

        Map<String, String> tags=new HashMap<String, String>();
        tags.put("cpu","1");
        tags.put("host","webserver01");

        List<TSDBDataPoint> datas=new ArrayList<TSDBDataPoint>();
        TSDBDataPoint point=new TSDBDataPoint(new Date().getTime(),1,tags);
        datas.add(point);
        AddDataUtil.addData(metricName,datas);
    }
}
