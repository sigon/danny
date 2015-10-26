package org.danny.common.wechat.execute.base;


import org.danny.common.wechat.MessageUtil;
import org.danny.common.wechat.domain.resp.BaseMessage;

import java.util.Map;


/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-6-23
 *         Time: 下午4:56
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public abstract class BaseExecute {

    public String doTrans(String param, Map<String, String> map) throws Exception{
        BaseMessage message = messageFactory();
        message.setCreateTime(System.currentTimeMillis());
        message.setFromUserName(map.get("ToUserName"));
        message.setToUserName(map.get("FromUserName"));
        execute(message, param, map);
        return MessageUtil.toJaxbXml(message);
    }
    public abstract void execute(BaseMessage message, String param, Map<String, String> map) throws Exception;

    public abstract BaseMessage messageFactory();

    public abstract Map<String,Class> getClassMap();
}
