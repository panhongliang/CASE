package com.phl.jndi;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.DirContext;
import javax.naming.directory.Attributes;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016-06-28.
 */
public class Getattr {
    public static void main(String[] args) throws Exception{
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");

        DirContext ctx = new InitialDirContext(env);
        Attributes attrs = ctx.getAttributes("cn = Ted Geisel, ou=People");
        attrs.get("sn").get();
    }
}
