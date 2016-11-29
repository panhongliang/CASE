package com.phl.jndi;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016-06-28.
 */
public class Lookup {
    public static void main(String[] args) throws Exception {
        String name="D:\\Foxmail\\BugReport.exe";
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.fscontext.RefFSContextFactory");

        Context ctx = new InitialContext(env);
        Object obj = ctx.lookup(name);
        System.out.println(name + " is bound to: " + obj);
    }
}
