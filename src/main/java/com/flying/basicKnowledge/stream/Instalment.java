package com.flying.basicKnowledge.stream;

import java.math.BigDecimal;

public class Instalment {
    private Long numInstalment;

    private Integer typeInstalment;

    private BigDecimal valueInstalment = BigDecimal.valueOf(50.09);

    private BigDecimal valueInstalmentRatio = BigDecimal.valueOf(45.08);

    private String status;

    private Integer version;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTypeInstalment() {
        return typeInstalment;
    }

    public void setTypeInstalment(Integer typeInstalment) {
        this.typeInstalment = typeInstalment;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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
