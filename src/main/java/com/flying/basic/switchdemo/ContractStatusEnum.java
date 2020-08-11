package com.flying.basic.switchdemo;

public enum ContractStatusEnum {
    APPROVED("a"),
    DECLINED("d"),
    CANCELLED("t");

    private String status;

    private ContractStatusEnum(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static boolean isInclude(String t) {
        boolean include = false;
        for (ContractStatusEnum item : ContractStatusEnum.values()) {
            if (t.equals(item)) {
                include = true;
                break;
            }
        }
        return include;
    }
}
