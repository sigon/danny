package org.danny.common.wechat.domain.tree;

import java.util.Map;

/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-7-18
 *         Time: 下午6:10
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public class Tree {
    private String type;
    private String key;
    private String exec;
    private String param;
    private Map<String, Tree> map;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExec() {
        return exec;
    }

    public void setExec(String exec) {
        this.exec = exec;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Map<String, Tree> getMap() {
        return map;
    }

    public void setMap(Map<String, Tree> map) {
        this.map = map;
    }
}
