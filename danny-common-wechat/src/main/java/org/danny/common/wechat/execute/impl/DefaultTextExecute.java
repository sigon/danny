package org.danny.common.wechat.execute.impl;

import org.danny.common.wechat.execute.base.TextExecute;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultTextExecute")
public class DefaultTextExecute extends TextExecute {
    @Override
    public String getContent(String param, Map<String, String> map) {
        return param;
    }
}
