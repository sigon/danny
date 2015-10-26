package org.danny.common.wechat.domain.req;

/**
 *         Date: 14-7-18
 *         Time: 下午3:32
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
