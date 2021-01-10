package com.flying.com.didi.Q1;

/**
 * 三、super和this的异同：
 * super:　它引用当前对象的直接父类中的成员（用来访问直接父类中被隐藏的父类中成员数据或函数，基类与派生类中有相同成员定义时如：super.变量名    super.成员函数据名（实参）
 * this：它代表当前对象名（在程序中易产生二义性之处，应使用this来指明当前对象；如果函数的形参与类中的成员数据同名，这时需用this来指明成员变量名）
 * super()和this()类似,区别是，super()在子类中调用父类的构造方法，this()在本类内调用本类的其它构造方法。
 * super()和this()均需放在构造方法内第一行。
 * 尽管可以用this调用一个构造器，但却不能调用两个。
 *
 * this和super不能同时出现在一个构造函数里面，因为this必然会调用其它的构造函数，其它的构造函数必然也会有super语句的存在，
 * 所以在同一个构造函数里面有相同的语句，就失去了语句的意义，编译器也不会通过。
 *
 * this()和super()都指的是对象，所以，均不可以在static环境中使用。包括：static变量,static方法，static语句块。
 * 从本质上讲，this是一个指向本对象的指针, 然而super是一个Java关键字。
 *
 * ***************类加载******************
 * java规定，在执行构造函数之bai前必须执行父du类的构造函数，直到这个类是zhijava.lang.Object类的构造函数。
 * 然而函数的dao入口是子类构造函数，因此任何构造函数第一句必须是执行父类构造函数，如果没有添加super关键字，那么编译器会为该构造函数第一句添加一个super()语句(你可以这么理解，当然编译以后并不是这样)。如果有super关键字显示的调用父类构造函数，就是用指定的那个父类构造函数，否则使用默认的无参数构造函数。也有一种情况例外，就是存在this关键字，调用本类其它构造函数，但是按照递归调用，最终还是会调用父类构造函数。
 * 如果你继承的父类没有无参数构造函数，那么你这个类第一句必须显示的调用super关键字来调用父类对应的有参数构造函数，否则不能通过编译。
 */
class Person{
    protected String name;

    public Person(String name) {
        this.name = name;
    }

}

class Student extends Person{
    private String name;

    public Student(String name, String name1) {
        super(name);
        this.name = name1;
    }
    public Student(String name ) {
        super(name);
        this.name = name;
    }

    public void getInfo(){
        System.out.println(this.name);      //Child
        System.out.println(super.name);     //Father
    }

}
public class Test {

    public static void main(String[] args) {
        Student s1 = new Student("Father","Child");
        Student s2 = new Student("Father");
        s1.getInfo();
        s2.getInfo();

    }

}
