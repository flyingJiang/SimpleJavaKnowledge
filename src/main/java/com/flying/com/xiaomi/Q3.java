package com.flying.com.xiaomi;

/**
 * new一个对象，内存是如何分配的[https://blog.csdn.net/qq_32379477/article/details/90239891]
 * 1，首先到常量池中找类的带路径全名，然后检查对应的字节码是否已被加载，解析，验证，初始化，如果没有先执行类加载过程(class.forname())。
 * 2，类加载过程完成后，虚拟机会为对象分配内存。分配内存有两种方式，根据使用的垃圾收集器的不同使用不同的分配机制。
 * （1）指针碰撞，当虚拟机使用复制算法或标记整理算法实现的垃圾收集器时，内存区域都是规整的，这时候使用指针碰撞分配内存，用过的内存放在一边，空闲的内存在另一边，中间用一个指针作为分界点，当需要为新对象分配内存时只需把指针向空闲的一边移动一段与对象大小相等的距离。
 * （2）空闲列表，当虚拟机使用标记清除算法实现的垃圾收集器时，内存都是碎片化的，那虚拟机就要记录哪块内存是可用的，当需要分配内存时，找一块足够大的内存空间给对象实例，并更新记录。
 * 3，设置对象头信息，如所属类，元数据信息，哈希码，gc分代年龄，等等。
 * 4，调用对象的init()方法,根据传入的属性值给对象属性赋值。
 * 5，在线程栈中新建对象引用，并指向堆中刚刚新建的对象实例。
 *
 * 4.对象初始化，顺序：
 * (1) 父类静态对象，静态代码块
 * (2)子类静态对象，静态代码块
 * (3)父类非静态对象，非静态代码块
 * (4)父类构造函数
 * (5)子类非静态对象，非静态代码块
 * (6)子类构造函数
 * [https://blog.csdn.net/qq_33824312/article/details/62858138?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.compare&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.compare]
 * SubClass sub = new SubClass();
 * 这句话到底做了什么事情呢？
 * 1.javac编译.java源文件形成.class字节码文件;
 * 2.new SubClass()对象时，先检查有没有父类，有父类，类加载器(ClassLoader)先将父类的Class文件读入内存，创建一个java.lang.Class对象，然后加载子类，类加载器将子类的Class文件读入内存，创建一个java.lang.Class对象;
 * 3.先初始化父类的静态属性，再初始化父类的静态代码块；
 * 4.再初始化子类的静态属性，再初始化子类的静态代码；
 * 5.在堆内存中分配内存空间，分配内存地址，此时是因为父类的特有属性才在堆内存中为父类对象分配空间。
 * 6.初始化父类的特有属性。
 * 7.初始化父类的构造代码块。
 * 8.初始化父类对象相应的构造方法。
 * 9.在堆内存中分配内存空间，分配内存地址，此时是因为子类的特有属性才在堆内存中为子类对象分配空间的。
 * 10.初始化子类的特有属性。
 * 11.初始化子类的构造代码块。
 * 12.初始化子类相应的构造方法。
 * 13.将子类的内存地址赋值给栈中的引用对象。
 *
 * 创建String对象的方式的讨论——引号内包含文本。
 *
 * 这种方式是String特有的，并且它与new的方式存在很大区别。
 * String str="abc";
 * 毫无疑问，这行代码创建了一个String对象。
 * String a="abc";  String b="abc";   那这里呢？
 * 答案还是一个。
 * String a="ab"+"cd";   再看看这里呢？
 * 答案是三个。
 * 只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中。对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中
 */
public class Q3 {
}
