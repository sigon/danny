package org.danny.common.wechat.domain.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Image {
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
