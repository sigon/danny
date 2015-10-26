package org.danny.common.wechat.manager.impl;

import org.danny.common.wechat.TreeUtil;
import org.danny.common.wechat.domain.constant.WechatConstant;
import org.danny.common.wechat.domain.tree.Tree;
import org.danny.common.wechat.execute.base.BaseExecute;
import org.danny.common.wechat.manager.WechatManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: sigon
 * Time: 15/10/23 下午11:08
 * Project: danny
 * @site: www.sigon.cn
 */
@Service("wechatManagerImpl")
public class WechatManagerImpl implements WechatManager {
    private static final Logger log = LoggerFactory.getLogger(WechatManagerImpl.class);

    @Resource(name = "executeMap")
    private Map<String, BaseExecute> executeMap;

    @Override
    public String processRequest(Map<String, String> map) {
        Tree tree = TreeUtil.exec(map);
        if(tree == null){
            return null;
        }
        BaseExecute execute = executeMap.get(tree.getExec());
        try {
            log.info(String.format("MSG_TYPE:%s FROM_USER_NAME:%s EVENT:%s EVENT_KEY:%s", map.get(WechatConstant.MsgType), map.get(WechatConstant.FromUserName), WechatConstant.Event, WechatConstant.EventKey));
            String result =  execute.doTrans(tree.getParam(), map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
