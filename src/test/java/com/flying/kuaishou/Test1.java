package com.flying.kuaishou;

import java.util.List;

import org.junit.Test;

public class Test1 {
    @Test
    public void m1(){
        List[]  p = new List[10];
        //泛型
//        https://www.cnblogs.com/coprince/p/8603492.html
        //在java中是”不能创建一个确切的泛型类型的数组”的。
//        List<String>[] p1 = new List<String>[10];
        List<?> [] l = new List<?>[10];
        List<String>[] p1 = new List[10];

        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
//传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<Integer>(123456);
//        Generic<Integer> genericInteger1 = new Generic<int>(123456);

//传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");
//        Log.d("泛型测试","key is " + genericInteger.getKey());
//        Log.d("泛型测试","key is " + genericString.getKey());

        //3.某文件多列，tab隔开，统计第三列单词，按照单词出现的次数，做倒序展示，并同时展示次数
        //4.
        //@ControllerAdvice 是否可以捕获 servlet 里面 filter的异常
        //maven解决版本冲突

    }
}

//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
class Generic<T>{
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    //在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
//public <T> T showKeyName(Generic<T extends Number> container)，编译器会报错："Unexpected bound"
    public <T extends Number> T showKeyName(Generic<T> container){
        System.out.println("container key :" + container.getKey());
        T test = container.getKey();
        return test;
    }
}
