package com.flying.design.singleton;

/**
 * 枚举的方式是比较少见的一种实现方式，但是看上面的代码实现，却更简洁清晰。并且她还自动支持序列化机制，绝对防止多次实例化。
 */
public enum Singleton4 {
    INSTANCE;
    public void anyMethod(){}
}
