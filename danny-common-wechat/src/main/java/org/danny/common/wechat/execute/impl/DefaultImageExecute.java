package org.danny.common.wechat.execute.impl;

import org.danny.common.wechat.execute.base.ImageExecute;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultImageExecute")
public class DefaultImageExecute extends ImageExecute {
    @Override
    public String getMediaId(String param, Map<String, String> map) {
        return map.get("MediaId");
    }
}
