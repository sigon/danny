package org.danny.common.wechat.domain.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-8
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class UserMember {
    private Map<String, String> member = new HashMap<String, String>();
    private Tree tree ;
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Map<String, String> getMember() {
        return member;
    }

    public void setMember(Map<String, String> member) {
        this.member = member;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
