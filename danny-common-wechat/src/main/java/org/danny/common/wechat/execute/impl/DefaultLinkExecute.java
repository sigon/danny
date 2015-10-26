package org.danny.common.wechat.execute.impl;

import org.danny.common.wechat.execute.base.TextExecute;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-19
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultLinkExecute")
public class DefaultLinkExecute extends TextExecute {

    @Override
    public String getContent(String param, Map<String, String> map) {
        return "您发送的是链接消息！";
    }
}
