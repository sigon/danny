package org.danny.common.wechat.manager;

import java.util.Map;

/**
 * 核心服务类
 */
public interface WechatManager {
    /**
     * 处理微信发来的请求
     *
     * @param requestMap
     * @return
     */
    public String processRequest(Map<String, String> requestMap);
}
