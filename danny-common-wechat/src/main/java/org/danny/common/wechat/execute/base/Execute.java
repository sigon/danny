package org.danny.common.wechat.execute.base;

import java.util.Map;

/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-6-23
 *         Time: 下午7:35
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public interface Execute<K,V> {
    public K getParam(String body, Map<String, Class> classMap);
}
