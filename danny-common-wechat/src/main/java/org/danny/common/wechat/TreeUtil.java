package org.danny.common.wechat;

import org.apache.commons.collections.CollectionUtils;
import org.danny.common.wechat.domain.constant.WechatConstant;
import org.danny.common.wechat.domain.tree.Tree;
import org.danny.common.wechat.domain.tree.UserMember;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sigon
 *         Date: 14-7-18
 *         Time: 下午6:08
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright
 */
public class TreeUtil {
    private static final String TREE_XML_PATH = "tree.xml";
    private static Map<String, Tree> map;
    static {
        init();
    }
    private static void init(){
        try {
            File treeXmlFile = new ClassPathResource(TREE_XML_PATH).getFile();

            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(treeXmlFile);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            Element ele = root.element("main");
            map = new HashMap<String, Tree>();
            map.put("main", parseElement(ele));
            map.put("default", parseElement(root.element("default")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Tree parseElement(Element ele) {
        Tree tree = new Tree();
        tree.setType(ele.attributeValue("type"));
        tree.setExec(ele.attributeValue("exec"));
        tree.setKey(ele.attributeValue("key"));
        tree.setParam(ele.attributeValue("param"));

        List list = ele.elements();

        if(CollectionUtils.isEmpty(list)){
            return tree;
        }
        Map<String, Tree> map = new HashMap<String, Tree>();
        for(Object o:list){
            Element e = (Element)o ;
            map.put(e.attributeValue("key"), parseElement(e));
        }

        tree.setMap(map);
        return tree;
    }
    public static Tree exec(Map<String, String> param){
        String type = param.get(WechatConstant.MsgType);
        String userName = param.get(WechatConstant.FromUserName);
        String content = null;
        if(type.equals("text")){
            content = param.get(WechatConstant.Content);
        }else if(type.equals("event")){
            String event = param.get(WechatConstant.Event);
            if(event.toUpperCase().equals("CLICK")){
                content = "event-click:" + param.get(WechatConstant.EventKey);
            }else if (event.toUpperCase().equals("VIEW")){
                content = "event-view";
            }

        }
        //返回首页
        if("0".equals(content)){
            UserMemberUtil.deleteUserMember(userName);
        }

        UserMember userMember = UserMemberUtil.getMember(userName);
        if(userMember == null){
            userMember = UserMemberUtil.createUserMember(userName, map.get("main"));
        }

        Tree tree = findTree(content, param, userMember.getTree());
        if(tree.getMap() != null){
            userMember.setTree(tree);
        }

        return tree;
    }

    private static Tree findTree(String key, Map<String, String> param, Tree head) {
        Map<String, Tree> treeMap = head.getMap();
        Tree tree = treeMap.get(key);
        if(treeMap == null || tree == null){
            if(head.getExec() != null){
                return head;
            }
            return map.get("default").getMap().get("customer");
        }
        return tree;
    }
}
