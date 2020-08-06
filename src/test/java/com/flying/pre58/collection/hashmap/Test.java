package com.flying.pre58.collection.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;

/**
 * https://www.cnblogs.com/DDiamondd/p/10722278.html
 *
 * https://www.jb51.net/article/120668.htm
 *
 * 使用entrySet遍历map类集合KV，而不是使用keySet遍历K
 * 对于Map来说,遍历的方式都是一样的,大概都是有四种形式
 *
 * 直接遍历
 * 返回keySet() //public Set<K> keySet()
 * 返回Values() //    public Collection<V> values() {
 * 返回entrySet() //    public Set<Map.Entry<K,V>> entrySet() {
 *
 * 对于第四种方式可能会除了返回的可以直接打印外,还可以通过返回Map.Entry类来依次遍历该集合返回key和value值
 */
public class Test {

    private HashMap<String, String> map = new HashMap<>();

    @Before
    public void init(){
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        map.put("4","D");
    }


    @org.junit.Test
    public void m1(){
        //第一种：普通使用，二次取值
        System.out.println("\n通过Map.keySet遍历key和value：");
        for(Object key:map.keySet())
        {
            System.out.println("Key: "+key+" Value: "+map.get(key));
        }
    }

    @org.junit.Test
    public void m2(){

        //第二种
        System.out.println("\n通过Map.entrySet使用iterator遍历key和value: ");
        Iterator map1it=map.entrySet().iterator();
        while(map1it.hasNext())
        {
            // 一定是  Map.Entry
            Map.Entry<String, String> entry=(Map.Entry<String, String>) map1it.next();
            System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
        }
    }

    @org.junit.Test
    public void m3(){
        //第三种：推荐，尤其是容量大时
        System.out.println("\n通过Map.entrySet遍历key和value");
        for(Map.Entry<String, String> entry: map.entrySet())
        {
            System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
        }
    }

    @org.junit.Test
    public void m4(){
        //第四种
        System.out.println("\n通过Map.values()遍历所有的value，但不能遍历key");
        for(String v:map.values())
        {
            System.out.println("The value is "+v);
        }
    }
}
