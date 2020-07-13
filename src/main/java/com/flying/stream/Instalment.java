package com.flying.stream;

import java.math.BigDecimal;

public class Instalment {
    private Long numInstalment;

    private BigDecimal valueInstalment = BigDecimal.ZERO;

    private BigDecimal valueInstalmentRatio = BigDecimal.ZERO;

    public Long getNumInstalment() {
        return numInstalment;
    }

    public void setNumInstalment(Long numInstalment) {
        this.numInstalment = numInstalment;
    }

    public BigDecimal getValueInstalment() {
        return valueInstalment;
    }

    public void setValueInstalment(BigDecimal valueInstalment) {
        this.valueInstalment = valueInstalment;
    }

    public BigDecimal getValueInstalmentRatio() {
        return valueInstalmentRatio;
    }

    public void setValueInstalmentRatio(BigDecimal valueInstalmentRatio) {
        this.valueInstalmentRatio = valueInstalmentRatio;
    }
}
