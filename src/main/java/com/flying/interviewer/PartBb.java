package com.flying.interviewer;

/**
 * 泛型方法的基本用法
 * generateTest.showResult1(book); //err
 * https://www.cnblogs.com/coprince/p/8603492.html
 */
/*
public class PartBb {
    public static void main(String[] args) {
        Vovol vovol = new Vovol();
        Book book = new Book();
        GenerateTest<Car> generateTest = new GenerateTest<Car>();
        generateTest.showResult1(vovol);
        generateTest.showResult1(book);
        generateTest.showResult2(vovol);
        generateTest.showResult2(book);
        generateTest.showResult3(vovol);
        generateTest.showResult3(book);
    }
}

class Car {
    @Override
    public String toString() {
        return "car";
    }
}

class Vovol extends Car {
    @Override
    public String toString() {
        return "vovol";
    }
}

class Book {
    @Override
    public String toString() {
        return "book";
    }
}

class GenerateTest<T> {
    public void showResult1(T t){
        System.out.println(t.toString());
    }
    public <E> void showResult2(E t){
        System.out.println(t.toString());
    }
    public <T> void showResult3(T t){
        System.out.println(t.toString());
    }
}*/
/*

public class GenericFruit {
    class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

    class Apple extends Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }

    class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    class GenerateTest<T>{
        public void show_1(T t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t){
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        //apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(person);

        //使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);

        //使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);
    }
}
*/
