package com.flying.didi.Q3;

/**
 * 泛型
 * https://segmentfault.com/a/1190000014120746
 */

public class Test {
    public static void main(String[] args) {
        Vovol vovol = new Vovol();
        Book book = new Book();
        GenerateTest<Car> generateTest = new GenerateTest<Car>();
        generateTest.showResult1(vovol);
//        generateTest.showResult1(book); //err
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
}
