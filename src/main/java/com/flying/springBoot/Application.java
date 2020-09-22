package com.flying.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
//        HelloBean helloBean = (HelloBean) context.getBean("hello");
//        helloBean.sayHello();
    }
}
/**
 * Spring Boot 中的 starter 到底是什么 ?
 * 首先，这个 Starter 并非什么新的技术点，基本上还是基于 Spring 已有功能来实现的。首先它提供了一个自动化配置类，一般命名为 XXXAutoConfiguration ，在这个配置类中通过条件注解来决定一个配置是否生效（条件注解就是 Spring 中原本就有的），然后它还会提供一系列的默认配置，也允许开发者根据实际情况自定义相关配置，然后通过类型安全的属性注入将这些配置属性注入进来，新注入的属性会代替掉默认属性。正因为如此，很多第三方框架，我们只需要引入依赖就可以直接使用了。当然，开发者也可以自定义 Starter
 *
 * spring-boot-starter-parent 有什么用 ?
 * 我们都知道，新创建一个 Spring Boot 项目，默认都是有 parent 的，这个 parent 就是 spring-boot-starter-parent ，spring-boot-starter-parent 主要有如下作用：
 * 定义了 Java 编译版本为 1.8 。
 * 使用 UTF-8 格式编码。
 * 继承自 spring-boot-dependencies，这个里边定义了依赖的版本，也正是因为继承了这个依赖，所以我们在写依赖时才不需要写版本号。
 * 执行打包操作的配置。
 * 自动化的资源过滤。
 * 自动化的插件配置。
 * 针对 application.properties 和 application.yml 的资源过滤，包括通过 profile 定义的不同环境的配置文件，例如 application-dev.properties 和 application-dev.yml。
 *
 *运行 Spring Boot 有哪几种方式？
 * 1）打包用命令或者放到容器中运行
 * 2）用 Maven/ Gradle 插件运行
 * 3）直接执行 main 方法运行
 *
 */
