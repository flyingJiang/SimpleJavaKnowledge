package com.flying.xiaomi;

/**
 * 什么情况会引起栈满[https://www.cnblogs.com/Javajishuzhai/p/11335309.html]
 * 3）栈溢出： java.lang.StackOverflowError : Thread Stack space
 * 栈溢出了，JVM依然是采用栈式的虚拟机，这个和C和Pascal都是一样的。函数的调用过程都体现在堆栈和退栈上了。
 * 调用构造函数的 “层”太多了，以致于把栈区溢出了。 通常来讲，一般栈区远远小于堆区的，因为函数调用过程往往不会多于上千层，
 * 而即便每个函数调用需要 1K的空间（这个大约相当于在一个C函数内声明了256个int类型的变量），那么栈区也不过是需要1MB的空间。
 * 通常栈的大小是1-2MB的。通俗一点讲就是单线程的程序需要的内存太大了。 通常递归也不要递归的层次过多，很容易溢出。
 * 解决方法 ：1：修改程序。2：通过 -Xss: 来设置每个线程的Stack大小即
 */
public class Q4 {
}
