package com.phl.test1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by panhongliang on 16/1/11.
 */
public class R {
    int a;
    String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public R() throws IOException {
        Properties properties=new Properties();
 //       InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream("a.properties");
        ClassLoader classLoader= getClass().getClassLoader();
        InputStream inputStream2=classLoader.getResource("a.properties").openStream();
        //InputStream inputStream3= getClass().getResource("/com.phl.amodel/a.properties").openStream();
        try {
            properties.load(inputStream2);
        } catch (IOException e) {
            e.printStackTrace();
        }
       String sa= (String) properties.get("a");
        a=Integer.parseInt(sa);
        String sb= (String) properties.get("b");
        b=sb;
    }
}
