package com.flying.design;

/**
 * 双检锁，又叫双重校验锁，综合了懒汉式和饿汉式两者的优缺点整合而成。看上面代码实现中，特点是在synchronized关键字内外都加了一层 if 条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。
 *
 * 好了，上面就是单例模式的五种主要写法。我们来总结下，
 * 一般情况下，懒汉式（包含线程安全和线程不安全梁总方式）都比较少用；
 * 饿汉式和双检锁都可以使用，可根据具体情况自主选择；
 * 在要明确实现 lazy loading 效果时，可以考虑静态内部类的实现方式；
 * 若涉及到反序列化创建对象时，大家也可以尝试使用枚举方式。
 */
public class Singleton {
    private volatile static Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
