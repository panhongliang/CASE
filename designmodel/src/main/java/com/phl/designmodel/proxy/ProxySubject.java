package com.phl.designmodel.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 通过工具类ProxyUtils生成的动态代理类
 */
public final class ProxySubject extends Proxy
        implements TargetInterface
{
    private static Method m1;
    private static Method m0;
    private static Method m3;
    private static Method m2;

    public ProxySubject(InvocationHandler paramInvocationHandler)

    {
        super(paramInvocationHandler);
    }

    public final boolean equals(Object paramObject)

    {
        try
        {
            return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
        }
        catch (Error localError)
        {
            throw localError;
        }
        catch (Throwable localThrowable)
        {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final int hashCode()

    {
        try
        {
            return ((Integer)this.h.invoke(this, m0, null)).intValue();
        }
        catch (Error localError)
        {
            throw localError;
        }
        catch (Throwable localThrowable)
        {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final void dosth(int paramInt1, int paramInt2)

    {
        try
        {
            this.h.invoke(this, m3, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
            return;
        }
        catch (Error localError)
        {
            throw localError;
        }
        catch (Throwable localThrowable)
        {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final String toString()

    {
        try
        {
            return ((String)this.h.invoke(this, m2, null));
        }
        catch (Error localError)
        {
            throw localError;
        }
        catch (Throwable localThrowable)
        {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    static
    {
        try
        {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m3 = Class.forName("com.phl.designmodel.proxy.TargetInterface").getMethod("dosth", new Class[] { Integer.TYPE, Integer.TYPE });
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);

        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
            throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
        }
    }
}