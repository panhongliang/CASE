package com.phl.designmodel.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by panhongliang on 16/2/25.
 */
public class ProxyUtils {

    public static void saveProxyClass(Class[] interfaces) {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, interfaces);
        try
        {
            FileOutputStream out = new FileOutputStream("/Users/panhongliang/Documents/"+ name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
