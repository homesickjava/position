package com.interview.equitypositions.pojo;

import lombok.Data;

@Data
public class Position extends BasePojo{
    // trade id
    private int tradeId;

    //security identifier for trade
    private String securityCode;

    //quantity of trade
    private int count;

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }
}
