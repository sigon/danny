package org.danny.common.wechat.domain.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-20
 * Time: 上午12:31
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Articles {
    private List<Article> item;

    public List<Article> getItem() {
        return item;
    }

    public void setItem(List<Article> item) {
        this.item = item;
    }
}
