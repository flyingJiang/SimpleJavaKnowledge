package com.flying.spring.impl;

import com.flying.spring.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 踩坑! spring事务,非事务方法与事务方法执行相互调用
 * ----------------------------------
 * 方法A调用方法B:
 * 下面是解决方案:
 *    1.把方法B抽离到另外一个XXService中去,并且在这个Service中注入XXService,使用XXService调用方法B;
 *       显然,这种方式一点也不优雅,且要产生很多冗余文件,看起来很烦,实际开发中也几乎没人这么做吧?.反正我不建议采用此方案;
 *    2.通过在方法内部获得当前类代理对象的方式,通过代理对象调用方法B
 *    上面说了:动态代理最终都是要调用原始对象的，而原始对象在去调用方法时，是不会再触发代理了！
 *     所以我们就使用代理对象来调用,就会触发事务;
 * 综上解决方案,我觉得第二种方式简直方便到炸. 那怎么获取代理对象呢? 这里提供两种方式:
 *    1.使用 ApplicationContext 上下文对象获取该对象;
 *    2.使用 AopContext.currentProxy() 获取代理对象,但是需要配置exposeProxy=true
 * 我这里使用的是第二种解决方案,具体操作如下:
 * springboot启动类加上注解:@EnableAspectJAutoProxy(exposeProxy = true)
 * -------------------------------------------
 * spring 在扫描bean的时候会扫描方法上是否包含@Transactional注解，如果包含，spring会为这个bean动态地生成一个子类（即代理类，proxy），代理类是继承原来那个bean的。
 * 此时，当这个有注解的方法被调用的时候，实际上是由代理类来调用的，代理类在调用之前就会启动transaction。
 * 然而，如果这个有注解的方法是被同一个类中的其他方法调用的，那么该方法的调用并没有通过代理类，而是直接通过原来的那个bean，
 * 所以就不会启动transaction，我们看到的现象就是@Transactional注解无效。
 * ---------------------------------------
 * 在一个Service内部，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务.
 * 1. spring采用动态代理机制来实现事务控制，而动态代理最终都是要调用原始对象的，而原始对象在去调用方法时，是不会再触发代理了！
 * 2. Spring的事务管理是通过AOP实现的，其AOP的实现对于非final类是通过cglib这种方式，即生成当前类的一个子类作为代理类，然后在调用其下的方法时，
 * 会判断这个方法有没有@Transactional注解，如果有的话，则通过动态代理实现事务管理(拦截方法调用，执行事务等切面)。
 * 当b()中调用a()时，发现b()上并没有@Transactional注解，所以整个AOP代理过程(事务管理)不会发生。
 *
 */
public class TestImpl implements Test {
    @Override
    public void a() {
        b();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void b() {

    }
}
