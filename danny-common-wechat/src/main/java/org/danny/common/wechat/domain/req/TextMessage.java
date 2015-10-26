package org.danny.common.wechat.domain.req;

/**
 *         Date: 14-7-18
 *         Time: 下午3:32
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
