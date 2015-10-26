package org.danny.common.wechat;

import org.apache.commons.lang.time.DateUtils;
import org.danny.common.wechat.domain.tree.Tree;
import org.danny.common.wechat.domain.tree.UserMember;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-8
 * Time: 下午10:06
 * To change this template use File | Settings | File Templates.
 */
public class UserMemberUtil {
    private static Integer MEMBER_CACHE_TIME = 5;
    private static Map<String, UserMember> map;
    static {
        map = new HashMap<String, UserMember>();
    }
    public static UserMember getMember(String name){
        UserMember member = map.get(name);
        if(member == null){
            return null;
        }
        if(System.currentTimeMillis() > member.getTime()){
            return null;
        }
        return member;
    }
    public static UserMember createUserMember(String name, Tree tree){
        UserMember member = new UserMember();
        member.setTree(tree);
        map.put(name, member);
        member.setTime(DateUtils.addMinutes(new Date(), MEMBER_CACHE_TIME).getTime());
        return member;
    }
    public static void deleteUserMember(String name){
        map.remove(name);
    }
}
