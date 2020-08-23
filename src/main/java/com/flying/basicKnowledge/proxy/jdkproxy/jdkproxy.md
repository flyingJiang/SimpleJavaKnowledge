# [JDK动态代理](https://www.cnblogs.com/zuidongfeng/p/8735241.html)
  JDK动态代理是代理模式的一种实现方式，其只能代理接口。

## 使用步骤
   1、 新建一个接口
   2、 为接口创建一个实现类
   3、 创建代理类实现java.lang.reflect.InvocationHandler接口
   4、 测试

##  大概流程
   1、为接口创建代理类的字节码文件
   2、使用ClassLoader将字节码文件加载到JVM
   3、创建代理类实例对象，执行对象的目标方法

   动态代理涉及到的主要类：
   java.lang.reflect.Proxy
   java.lang.reflect.InvocationHandler
   java.lang.reflect.WeakCache
   sun.misc.ProxyGenerator

   [使用JDK动态代理的五大步骤：](https://blog.csdn.net/yhl_jxy/article/details/80586785)
   1）通过实现InvocationHandler接口来自定义自己的InvocationHandler；
   2）通过Proxy.getProxyClass获得动态代理类；
   3）通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)；
   4）通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入；
   5）通过代理对象调用目标方法；

## 可看到
   1、代理类继承了Proxy类并且实现了要代理的接口，由于java不支持多继承，所以JDK动态代理不能代理类
   2、重写了equals、hashCode、toString
   3、有一个静态代码块，通过反射或者代理类的所有方法
   4、通过invoke执行代理类中的目标方法doSomething
   ```java
   //
   // Source code recreated from a .class file by IntelliJ IDEA
   // (powered by Fernflower decompiler)
   //

   package com.sun.proxy;

   import com.lnjecit.proxy.Subject;
   import java.lang.reflect.InvocationHandler;
   import java.lang.reflect.Method;
   import java.lang.reflect.Proxy;
   import java.lang.reflect.UndeclaredThrowableException;

   public final class $Proxy0 extends Proxy implements Subject {
       private static Method m1;
       private static Method m3;
       private static Method m2;
       private static Method m0;

       public $Proxy0(InvocationHandler var1) throws  {
           super(var1);
       }

       public final boolean equals(Object var1) throws  {
           try {
               return ((Boolean)super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
           } catch (RuntimeException | Error var3) {
               throw var3;
           } catch (Throwable var4) {
               throw new UndeclaredThrowableException(var4);
           }
       }

       public final void doSomething() throws  {
           try {
               super.h.invoke(this, m3, (Object[])null);
           } catch (RuntimeException | Error var2) {
               throw var2;
           } catch (Throwable var3) {
               throw new UndeclaredThrowableException(var3);
           }
       }

       public final String toString() throws  {
           try {
               return (String)super.h.invoke(this, m2, (Object[])null);
           } catch (RuntimeException | Error var2) {
               throw var2;
           } catch (Throwable var3) {
               throw new UndeclaredThrowableException(var3);
           }
       }

       public final int hashCode() throws  {
           try {
               return ((Integer)super.h.invoke(this, m0, (Object[])null)).intValue();
           } catch (RuntimeException | Error var2) {
               throw var2;
           } catch (Throwable var3) {
               throw new UndeclaredThrowableException(var3);
           }
       }

       static {
           try {
               m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
               m3 = Class.forName("com.lnjecit.proxy.Subject").getMethod("doSomething");
               m2 = Class.forName("java.lang.Object").getMethod("toString");
               m0 = Class.forName("java.lang.Object").getMethod("hashCode");
           } catch (NoSuchMethodException var2) {
               throw new NoSuchMethodError(var2.getMessage());
           } catch (ClassNotFoundException var3) {
               throw new NoClassDefFoundError(var3.getMessage());
           }
       }
   }
   ```
