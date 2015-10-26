package org.danny.common.wechat.execute;

import org.danny.common.secret.SHA;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 石光
 *         Date: 14-7-18
 *         Time: 上午11:42
 *         微信接入接口
 */
@Service("joinWeiXinExecute")
public class JoinWeiXinExecute{
    private static final String token = "sigon1q2w3e";
    public String execute(String client, Map<String, String> param) throws Exception {
        String signature = param.get("signature");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");
        String echostr = param.get("echostr");

        String[] str = {token, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        String digest = new SHA().getDigestOfString(bigStr.toString().getBytes()).toLowerCase();
        if(digest.equals(signature)){
            return echostr;  //To change body of implemented methods use File | Settings | File Templates.
        }else{
            return "error";
        }

    }
    public static void main(String[] args){
        // /client/joinWeiXin.action?signature=723395540410bd9e6e30888ac67bf731f249a0b3&echostr=8349930807965106042&timestamp=1405665146&nonce=365486933
        Map<String, String> map = new HashMap<String, String>();
        map.put("signature", "723395540410bd9e6e30888ac67bf731f249a0b3");
        map.put("timestamp", "1405665146");
        map.put("nonce", "365486933");
        map.put("echostr", "8349930807965106042");
        //System.out.println(new SHA().getDigestOfString("14056651468349930807965106042sigon1q2w3e".toString().getBytes()).toLowerCase());
        try {
            new JoinWeiXinExecute().execute(null, map);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
