package com.flying.stringT;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-04-28 16:14
 **/
public class StringTest {
    @Test
    public void case1(){
        String str = null;
        if (StringUtils.isEmpty(str)){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }
    @Test
    public void case3(){
        T1 t1 = new T1();
        t1.setName("1");
        if (!t1.getName().equals(null)){
            System.out.println("1");
        }
        System.out.println("2");
    }
    /*
    @Test
    public void case2(){

        String str = "a";
        StringUtils.con
        String[] lableAddeds = str.split()
                StringUtils.split(str, ",");
        if (CollectionUtils.isEmpty(lableAddeds)){
            lableAddeds = new String[1];
            lableAddeds[0]=str;
        }
        if (ArrayUtils.isEmpty(lableReduceds))
        boolean has = false;
        String[] strOne= {"c","b","a","d"};
        String[] strTwo= {"a","b","d","g"};
        HashSet<String> set = new HashSet<>(Arrays.asList(strOne));
        set.retainAll(Arrays.asList(strTwo));
        if(set.size() > 0){
            has =  true;
        }
        System.out.println(has);
    }*/

}
@Data
class T1{
    private String name;
    private String pwd;
}
