package com.flying.collection.map;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: SimpleJavaKnowledge
 * @description: 参看 https://blog.csdn.net/qq_33982232/article/details/83044187
 * @author: jiangjianfei
 * @create: 2021-03-18 16:59
 **/
public class MapTest {
    private static Map<String, String> map;

    @BeforeClass
    public static void init() {
        map = new LinkedHashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
    }

    @Test
    public void caseForEach() {
        map.forEach((k, v) -> {
            print(k, v);
        });
    }

    @Test
    public void caseEntry() {
        for (Map.Entry<String, String> it : map.entrySet()) {
            print(it.getKey(), it.getValue());
        }
    }

    @Test
    public void caseIterator() {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> item = it.next();
            print(item.getKey(), item.getValue());
        }
    }

    @Test
    public void caseBoolean(){
        Boolean a = null;
        System.out.println(a.toString());
    }

    private void print(final String key, final String value) {
        System.out.println(key + "," + value);
    }
}
