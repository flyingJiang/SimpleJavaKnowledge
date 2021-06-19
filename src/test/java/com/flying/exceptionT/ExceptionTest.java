package com.flying.exceptionT;

import org.junit.Test;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-04-16 10:18
 **/
public class ExceptionTest {
    @Test
    public void case1(){
        try{
            System.out.println("case1 begin");
            method1();
        }catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
        System.out.println("case1 end");
    }

    private void method1() throws Exception{
        System.out.println("method1 begin");
        method2();
        System.out.println("method1 end");

    }

    private void method2() throws Exception{
        System.out.println("method2 begin");
        int i = 5/0;
        System.out.println("method2 end");
    }
    @Test
    public void case2(){
        try{
            System.out.println("1 begin");
            // int j = 5/0;

            try{
                System.out.println("1-1 begin");
                // int i = 5/0;
            }catch (Exception e){
                System.out.println("1-1 exception");
            }
            // int j = 5/0;

            try{
                System.out.println("1-2 begin");
                int i = 5/0;
            }catch (Exception e){
                System.out.println(e.getStackTrace().toString());
                System.out.println("1-2 exception");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("1 exception");
        }
        System.out.println("1 end");
    }
}
