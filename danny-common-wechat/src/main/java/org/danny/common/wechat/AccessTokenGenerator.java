package org.danny.common.wechat;

import org.danny.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import org.danny.common.util.HttpRequestProxy;

/**
 * @author: sigon
 * Time: 15/10/20 下午10:33
 * Project: codenote
 * @site: www.sigon.cn
 */
@Component
public class AccessTokenGenerator {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenGenerator.class);
    private static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;

    public String get(){
        Map tokenMap = new HashMap();
        tokenMap.put("grant_type", "client_credential");
        tokenMap.put("appid", appid);
        tokenMap.put("secret", secret);
        HttpRequestProxy hrp = new HttpRequestProxy();
        String tokenJson = hrp.doRequest(accessTokenUrl, tokenMap, null);
        WechatResult wechatResult = null;
        try {
            wechatResult = (WechatResult) JsonUtil.jsonToObject(tokenJson, WechatResult.class);
        } catch (Exception e) {
            log.error("json error:" + tokenJson , e);
        }
        String accessToken = wechatResult.getAccess_token();
        return accessToken;
    }
}
