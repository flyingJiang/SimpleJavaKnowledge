package com.flying.unit;

import org.junit.Test;

/**
 * https://blog.csdn.net/qq_40395278/article/details/78664762
 * <p>
 * String类其实是通过char数组来保存字符串的。
 */
public class StringTest {

    /**
     * false
     * true
     * false
     * 在前面一篇讲解关于JVM内存机制的一篇博文中提到 ，在class文件中有一部分 来存储编译期间生成的 字面常量以及符号引用，这部分叫做class文件常量池，在运行期间对应着方法区的运行时常量池。
     * <p>
     * 　　因此在上述代码中，String str1 = "hello world";和String str3 = "hello world"; 都在编译期间生成了 字面常量和符号引用，运行期间字面常量"hello world"被存储在运行时常量池（当然只保存了一份）。通过这种方式来将String对象跟引用绑定的话，JVM执行引擎会先在运行时常量池查找是否存在相同的字面常量，如果存在，则直接将引用指向已经存在的字面常量；否则在运行时常量池开辟一个空间来存储该字面常量，并将引用指向该字面常量。
     * <p>
     * 　　总所周知，通过new关键字来生成对象是在堆区进行的，而在堆区进行对象生成的过程是不会去检测该对象是否已经存在的。因此通过new来创建对象，创建出的一定是不同的对象，即使字符串的内容是相同的。
     *
     * 1）对于直接相加字符串，效率很高，因为在编译器便确定了它的值，也就是说形如"I"+"love"+"java"; 的字符串相加，在编译期间便被优化成了"Ilovejava"。这个可以用javap -c命令反编译生成的class文件进行验证。
     *
     * 　　对于间接相加（即包含字符串引用），形如s1+s2+s3; 效率要比直接相加低，因为在编译器不会对引用变量进行优化。
     *
     * 　　2）String、StringBuilder、StringBuffer三者的执行效率：
     *
     * 　　StringBuilder > StringBuffer > String
     *
     * 　　当然这个是相对的，不一定在所有情况下都是这样。
     *
     * 　　比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。
     *
     * 　　因此，这三个类是各有利弊，应当根据不同的情况来进行选择使用：
     *
     * 　　当字符串相加操作或者改动较少的情况下，建议使用 String str="hello"这种形式；
     *
     * 　　当字符串相加操作较多的情况下，建议使用StringBuilder，如果采用了多线程，则使用StringBuffer。
     */
    @Test
    public void test1() {
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");

        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str4);
    }
    @Test
    public void test2() {
        String string = "";
        for(int i=0;i<10000;i++){
            string += "hello";
        }
    }
    @Test
    public void test3() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<10000;i++){
            stringBuilder.append("hello");
        }
    }

    /**
     * 输出结果为：true。原因很简单，"hello"+2在编译期间就已经被优化成"hello2"，因此在运行期间，变量a和变量b指向的是同一个对象。
     */
    @Test
    public void test4() {
        String a = "hello2";
        String b = "hello" + 2;
        System.out.println((a == b));
    }

    /**
     * 输出结果为:false。由于有符号引用的存在，所以  String c = b + 2;
     * 不会在编译期间被优化，不会把b+2当做字面常量来处理的，因此这种方式生成的对象事实上是保存在堆上的。因此a和c指向的并不是同一个对象。javap -c得到的内容：
     */
    @Test
    public void test5() {
        String a = "hello2";
        String b = "hello";
        String c = b + 2;
        System.out.println((a == c));
    }

    /**
     * 输出结果为：true。对于被final修饰的变量，会在class文件常量池中保存一个副本，也就是说不会通过连接而进行访问，
     * 对final变量的访问在编译期间都会直接被替代为真实的值。那么String c = b + 2;
     * 在编译期间就会被优化成：String c = "hello" + 2; 下图是javap -c的内容：
     */
    @Test
    public void test6() {
        String a = "hello2";
        final String b = "hello";
        String c = b + 2;
        System.out.println((a == c));
    }

    /**
     * 输出结果为false。这里面虽然将b用final修饰了，但是由于其赋值是通过方法调用返回的，那么它的值只能在运行期间确定，因此a和c指向的不是同一个对象。
     */
    @Test
    public void test7() {
        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        System.out.println((a == c));
    }

    private String getHello() {
        return "hello";
    }

    /**
     * false
     * false
     * false
     * true
     * 这里面涉及到的是String.intern方法的使用。在String类中，intern方法是一个本地方法，在JAVA SE6之前，
     * intern方法会在运行时常量池中查找是否存在内容相同的字符串，如果存在则返回指向该字符串的引用，
     * 如果不存在，则会将该字符串入池，并返回一个指向该字符串的引用。因此，a和d指向的是同一个对象。
     */
    @Test
    public void test8() {
        String a = "hello";
        String b =  new String("hello");
        String c =  new String("hello");
        String d = b.intern();

        System.out.println(a==b);
        System.out.println(b==c);
        System.out.println(b==d);
        System.out.println(a==d);
    }
    /**
     * String str = new String("abc")创建了多少个对象？
     *
     * 而这道题目让人混淆的地方就是这里，这段代码在运行期间确实只创建了一个对象，即在堆上创建了"abc"对象。而为什么大家都在说是2个对象呢，
     * 这里面要澄清一个概念  该段代码执行过程和类的加载过程是有区别的。在类加载的过程中，确实在运行时常量池中创建了一个"abc"对象，
     * 而在代码执行过程中确实只创建了一个String对象。
     */
    @Test
    public void test9() {
        String str = new String("abc");
    }

    /**
     * 下面这段代码1）和2）的区别是什么？
     * 　1）的效率比2）的效率要高，1）中的"love"+"java"在编译期间会被优化成"lovejava"，而2）中的不会被优化。下面是两种方式的字节码：
     * 可以看出，在1）中只进行了一次append操作，而在2）中进行了两次append操作。
     */
    @Test
    public void test10() {
        String str1 = "I";
        //str1 += "love"+"java";        1)
        str1 = str1+"love"+"java";      //2)
    }

}
