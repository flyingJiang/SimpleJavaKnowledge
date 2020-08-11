package com.flying.basic.switchdemo;

import org.apache.commons.collections.EnumerationUtils;

public class Test {
    public static void main(String[] args) {
//        boolean f = ContractStatusEnum.isInclude("APPROVED1");
//        if (!f) return;
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
