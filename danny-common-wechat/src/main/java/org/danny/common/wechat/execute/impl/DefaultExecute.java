package org.danny.common.wechat.execute.impl;

import org.danny.common.wechat.execute.base.TextExecute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultExecute")
public class DefaultExecute extends TextExecute {
    @Resource(name = "fileTextExecute")
    private FileTextExecute fileTextExecute;
    @Override
    public String getContent(String param, Map<String, String> map) {
        return fileTextExecute.getContent("index.txt", map);
    }
}
