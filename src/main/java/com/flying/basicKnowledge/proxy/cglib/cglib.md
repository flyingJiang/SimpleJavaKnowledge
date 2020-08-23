# [CGLIB动态代理实现原理](https://blog.csdn.net/yhl_jxy/article/details/80633194)

## CGLIB介绍
   CGLIB(Code Generation Library)是一个开源项目！是一个强大的，高性能，高质量的Code生成类库，

   它可以在运行期扩展Java类与实现Java接口。Hibernate用它来实现PO(Persistent Object 持久化对象)字节码的动态生成。

   CGLIB是一个强大的高性能的代码生成包。它广泛的被许多AOP的框架使用，例如Spring AOP为他们提供

   方法的interception（拦截）。CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类。

   除了CGLIB包，脚本语言例如Groovy和BeanShell，也是使用ASM来生成java的字节码。当然不鼓励直接使用ASM，

   因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。

## 重点关注代理对象的sayHello方法：
   从代理对象反编译源码可以知道，代理对象继承于HelloService，拦截器调用intercept()方法，
   intercept()方法由自定义MyMethodInterceptor实现，所以，最后调用MyMethodInterceptor中
   的intercept()方法，从而完成了由代理对象访问到目标对象的动态代理实现。
   ```java
    public final void sayHello() {
        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
        if (var10000 == null) {
            CGLIB$BIND_CALLBACKS(this);
            var10000 = this.CGLIB$CALLBACK_0;
        }

        if (var10000 != null) {
            var10000.intercept(this, CGLIB$sayHello$0$Method, CGLIB$emptyArgs, CGLIB$sayHello$0$Proxy);
        } else {
            super.sayHello();
        }
    }
   ````
