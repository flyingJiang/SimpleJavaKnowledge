package com.flying.basicKnowledge.basic.switchdemo;

public class Test {
    public static void main(String[] args) {
//        boolean f = ContractStatusEnum.isInclude("APPROVED1");
//        if (!f) return;
        /**
         * Exception in thread "main" java.lang.IllegalArgumentException: No enum constant com.flying.basic.switchdemo.ContractStatusEnum.APPROVED1
         * 	at java.lang.Enum.valueOf(Enum.java:238)
         * 	at com.flying.basic.switchdemo.ContractStatusEnum.valueOf(ContractStatusEnum.java:3)
         * 	at com.flying.basic.switchdemo.Test.main(Test.java:9)
         */
        ContractStatusEnum contractEnum = ContractStatusEnum.valueOf("APPROVED1");
        switch(contractEnum) {
            case APPROVED:
                System.out.println("1");
                break;
            case DECLINED:
                System.out.println("11");
                break;
            case CANCELLED:
                System.out.println("111");
                break;
            default:
                System.out.println("1111");
        }
    }
}
