package net.sigon.danny.common.test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: sigon
 * Time: 15-1-20 下午3:44
 * Project: danny
 */
public class AddressItem {
    private String name;
    private List<AddressItem> sub;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressItem> getSub() {
        return sub;
    }

    public void setSub(List<AddressItem> sub) {
        this.sub = sub;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
