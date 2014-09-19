package net.sigon.danny.common.util;

import java.util.Arrays;
import java.util.Map;

/**
 *         Date: 14-7-18
 *         Time: 下午3:20
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public class WeixinValid {
    private static final String token = "sigon1q2w3e";

    public static Boolean valid(Map<String, String> param){
        String signature = param.get("signature");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");
        String echostr = param.get("echostr");

        String[] str = {token, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        String digest = new SHA().getDigestOfString(bigStr.toString().getBytes()).toLowerCase();
        return digest.equals(signature);
    }
}
