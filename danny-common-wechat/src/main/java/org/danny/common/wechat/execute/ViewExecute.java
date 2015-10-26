package org.danny.common.wechat.execute;

import org.danny.common.wechat.domain.resp.BaseMessage;
import org.danny.common.wechat.domain.resp.NewsMessage;
import org.danny.common.wechat.execute.base.BaseExecute;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-26
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
@Service("viewExecute")
public class ViewExecute extends BaseExecute {
    @Override
    public void execute(BaseMessage message, String param, Map<String, String> map) throws Exception {
        //todo 添加操作记录
    }

    @Override
    public BaseMessage messageFactory() {
        return new NewsMessage();
    }

    @Override
    public Map<String, Class> getClassMap() {
        return null;
    }
}
