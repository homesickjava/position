package com.interview.equitypositions.pojo;

import lombok.Data;

/**
 * pojo class, including trading info
 */
@Data
public class Trade extends BasePojo implements Comparable {

    //transaction id
    private int transactionId;

    // trade id
    private int tradeId;

    // version for one trade id
    private int version;

    //security identifier for trade
    private String securityCode;

    //quantity of trade
    private int quantity;

    //option: Insert/Update/Cancel
    private String option;

    //identify of buy or sell
    private String buyOrSell;


    @Override
    public int compareTo(Object o) {
        return this.transactionId - ((Trade)o).getTransactionId();
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

}
