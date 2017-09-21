package com.phl.serial;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-23.
 */
public class JavaSerial  {
    public static byte[] serialize(Object obj) throws Exception {
        if(obj==null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(obj);
        return os.toByteArray();
    }

    public static Object deserialize(byte[] by) throws Exception {
        if(by==null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }

    public static void output()throws Exception {
        FileOutputStream fos = new FileOutputStream("D:\\t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Obj o=new Obj();
        HashMap<String,String> map=new HashMap<>();
        map.put("d","e");
        o.setMap(map);
       oos.writeObject(o);
       oos.close();
    }
    public static void main(String[] args) throws Exception {
        JavaSerial.output();
    }
}
