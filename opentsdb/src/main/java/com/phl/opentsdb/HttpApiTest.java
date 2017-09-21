package com.phl.opentsdb;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.phl.opentsdb.query.http.OpenTsdbHttpQueryClient;
import org.opentsdb.client.request.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpApiTest {
    
    public static void main(String[] args)
        throws IOException {
        System.out.println(DateUtil.formatToString(new Date(1502176319776L), DateUtil.YYYY_MM_DD_HH_MM));
        
        OpenTsdbHttpQueryClient client = new OpenTsdbHttpQueryClient("http://master:4242");

        final List<Filter> filters = new ArrayList<Filter>();
        /* Filter f = new Filter();
        f.setType("wildcard");
        f.setGroupBy(Boolean.TRUE);
        f.setTagk("cpu");
        f.setFilter("*");
        filters.add(f);
        
        Filter f1 = new Filter();
        f1.setType("wildcard");
        f1.setGroupBy(Boolean.TRUE);
        f1.setTagk("host");
        f1.setFilter("*");
        filters.add(f1);
        */
        String res = client.queryData("sys.cpu.user", "2017-08-30 00:00:00", "2017-08-31 24:00:00",
            filters, "sum", null);
        System.out.println(res);
        if (res == null) {
            return;
        }
        List<Map> ja = JSONArray.parseArray(res, Map.class);
        for (Map map : ja) {
            System.out.println("metric:" + map.get("metric"));
            System.out.println("tags:" + map.get("tags"));
            Map<String, String> tags = JSON.parseObject(map.get("tags").toString(), Map.class);
            for (Entry<String, String> entry : tags.entrySet()) {
                System.out.println("tagkey:" + entry.getKey() + "," + "tagval:" + entry.getValue());
            }

            System.out.println("dps:" + map.get("dps"));
            Map<String, Object> dps = JSON.parseObject(map.get("dps").toString(), Map.class);

            for (Entry<String, Object> entry : dps.entrySet()) {
                System.out.println("date:" + DateUtil.formatToString(new Date(Long.valueOf(entry.getKey()) * 1000),
                    DateUtil.YYYY_MM_DD_HH_MM_SS) + "," + "count:" + entry.getValue());
            }
        }

    }

}
