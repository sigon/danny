package org.danny.common.forex;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: sigon
 * Time: 15-1-26 上午11:34
 * Project: danny
 */
public class ForexItem {
    private String symbol;
    private Integer xhBuy;
    private Integer xhSell;
    private Date modifyDate;

    public Integer getXhBuy() {
        return xhBuy;
    }

    public void setXhBuy(Integer xhBuy) {
        this.xhBuy = xhBuy;
    }

    public Integer getXhSell() {
        return xhSell;
    }

    public void setXhSell(Integer xhSell) {
        this.xhSell = xhSell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
