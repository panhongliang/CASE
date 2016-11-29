package com.phl.xml.digester;

import org.apache.commons.digester.Digester;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016-06-23.
 */
public class Main {

    Outer outer;

    public Outer getOuter() {
        return outer;
    }

    public void setOuter(Outer outer) {
        this.outer = outer;
    }

    public static void main(String[] args) {
        Main main=new Main();
        Digester digester=new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("Outer","com.phl.xml.digester.Outer","className");
        digester.addSetProperties("Outer");//设置outer的属性值
        digester.addSetNext("Outer","setOuter","com.phl.xml.digester.Outer");//把Outer设置为main的属性

        digester.addObjectCreate("Outer/Inner","com.phl.xml.digester.Inner");
        digester.addSetProperties("Outer/Inner");//设置Inner的属性值
        digester.addSetNext("Outer/Inner","setInner","com.phl.xml.digester.Inner");//把Inner设置为Outer的属性
        File file = new File("D:\\360CloudUI\\Cache\\1347785479\\test\\javacase\\src\\main\\java\\com\\phl\\xml\\digester\\abc.xml");
        FileInputStream fis = null;
        try {
            InputSource is =
                    new InputSource(file.toURI().toURL().toString());
            fis = new FileInputStream(file);
            is.setByteStream(fis);
            digester.push(main);//根元素为 main
            digester.parse(is);

            int a=1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }
}
