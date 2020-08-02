package com.flying.fanshe;

/**
 *
 * 反射机制的应用场景有哪些？
 * 反射是框架设计的灵魂。
 *
 * 在我们平时的项目开发过程中，基本上很少会直接使用到反射机制，但这不能说明反射机制没有用，实际上有很多设计、开发都与反射机制有关，例如模块化的开发，通过反射去调用对应的字节码；动态代理设计模式也采用了反射机制，还有我们日常使用的 Spring／Hibernate 等框架也大量使用到了反射机制。
 *
 * 举例：①我们在使用JDBC连接数据库时使用Class.forName()通过反射加载数据库的驱动程序；
 * ②Spring框架也用到很多反射机制，最经典的就是xml的配置模式。Spring 通过 XML 配置模式装载 Bean 的过程：
 * 1) 将程序内所有 XML 或 Properties 配置文件加载入内存中;
 * 2)Java类里面解析xml或properties里面的内容，得到对应实体类的字节码字符串以及相关的属性信息;
 * 3)使用反射机制，根据这个字符串获得某个类的Class实例;
 * 4)动态配置实例的属性
 *
 * Java获取反射的三种方法
 * 1.通过new对象实现反射机制 2.通过路径实现反射机制 3.通过类名实现反射机制
 */
public class FanShe {
    //获取反射机制三种方式
    public static void main(String[] args) throws ClassNotFoundException {
        //方式一(通过建立对象)
        Student stu = new Student();
        Class classobj1 = stu.getClass();
        System.out.println(classobj1.getName());
        //方式二（所在通过路径-相对路径）
        Class classobj2 = Class.forName("com.flying.fanshe.Student");
        System.out.println(classobj2.getName());
        //方式三（通过类名）
        Class classobj3 = Student.class;
        System.out.println(classobj3.getName());
    }
}
