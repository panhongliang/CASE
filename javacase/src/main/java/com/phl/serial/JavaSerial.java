package com.phl.serial;

import java.io.*;
import java.util.Date;

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

       oos.writeObject(new Obj());
      // oos.writeObject(new Date());

       oos.close();
    }
    public static void main(String[] args) throws Exception {
        JavaSerial.output();
    }
}
