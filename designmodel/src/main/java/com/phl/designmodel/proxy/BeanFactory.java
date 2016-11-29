package com.phl.designmodel.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by panhongliang on 16/2/25.
 */
public class BeanFactory {

    private static Map<String,Object> beanCacheMap=new HashMap<String, Object>();

    public  static Object getBean(Class c){
        if(beanCacheMap.containsKey(c.getCanonicalName())){
            return beanCacheMap.get(c.getCanonicalName());
        }else {
            ProxyHandler proxyHandler= null;
            try {
                proxyHandler = new ProxyHandler(c.newInstance());
                Object o= Proxy.newProxyInstance(c.getClassLoader(),
                        c.getInterfaces(),proxyHandler);
                beanCacheMap.put(o.getClass().getCanonicalName(),o);
               // ProxyUtils.saveProxyClass(c.getInterfaces());
                return o;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

   }
}
