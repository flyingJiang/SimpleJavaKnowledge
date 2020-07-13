package com.flying.stream;

import java.math.BigDecimal;

public class ContractInstalmentCalculationEvent {
    private Integer instalmentNumber;

    private BigDecimal amount;

    public Integer getInstalmentNumber() {
        return instalmentNumber;
    }

    public void setInstalmentNumber(Integer instalmentNumber) {
        this.instalmentNumber = instalmentNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
