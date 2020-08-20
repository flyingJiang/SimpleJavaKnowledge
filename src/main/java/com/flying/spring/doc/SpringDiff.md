# [Spring家族主流成员介绍](https://www.cnblogs.com/williamjie/p/11101020.html)

* 1.Spring 框架就像一个家族，有众多衍生产品例如 boot、security、jpa等等。但他们的基础都是Spring的ioc、aop等. ioc 提供了依赖注入的容器， aop解决了面向横切面编程，然后在此两者的基础上实现了其他延伸产品的高级功能；

* 2.SpringMvc是基于Servlet 的一个MVC框架主要解决WEB开发的问题，因为Spring的配置非常复杂，各种XML、JavaConfig、servlet处理起来比较繁琐；

* 3.为了简化开发者的使用，从而创造性地推出了SpringBoot框架，默认优于配置，简化了SpringMVC的配置流程；

但区别于SpringMVC的是，SpringBoot专注于微服务方面的接口开发，和前端解耦，虽然SpringBoot也可以做成SpringMVC前后台一起开发，但是这就有点不符合SpringBoot框架的初衷了；

* 4.对于SpringCloud框架来说，它和SpringBoot一样，注重的是微服务的开发，但是SpringCloud更关注的是全局微服务的整合和管理，相当于管理多个SpringBoot框架的单体微服务;

```java
class Test {
  public static void main(String[] args) {
      System.out.println("Hello  World!");
  }
}
```
