package org.danny.common.wechat.execute.impl;

import org.danny.common.wechat.MessageUtil;
import org.danny.common.wechat.domain.resp.BaseMessage;
import org.danny.common.wechat.domain.resp.CustomerMessage;
import org.danny.common.wechat.execute.base.BaseExecute;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午8:29
 * To change this template use File | Settings | File Templates.
 */
@Service("customerExecute")
public class CustomerExecute extends BaseExecute {

    private static Map<String, Class> classMap;
    static {
        classMap = new HashMap<String, Class>();
        classMap.put("xml", CustomerMessage.class);
    }
    @Override
    public void execute(BaseMessage message, String param, Map<String, String> map) throws Exception {
        message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER);
    }
    public BaseMessage messageFactory(){
        return new CustomerMessage();
    }

    public Map<String,Class> getClassMap(){
        return classMap;
    }

}
