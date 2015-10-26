package org.danny.common.wechat.execute.base;


import org.danny.common.wechat.MessageUtil;
import org.danny.common.wechat.domain.resp.Article;
import org.danny.common.wechat.domain.resp.Articles;
import org.danny.common.wechat.domain.resp.BaseMessage;
import org.danny.common.wechat.domain.resp.NewsMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午8:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class NewsExecute extends BaseExecute {

    private static Map<String, Class> classMap;
    static {
        classMap = new HashMap<String, Class>();
        classMap.put("xml", NewsMessage.class);
        classMap.put("Articles", Articles.class);
        classMap.put("item", Article.class);
    }
    @Override
    public void execute(BaseMessage message, String param, Map<String, String> map) throws Exception {
        message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        NewsMessage newsMessage = (NewsMessage)message;
        Articles articles = new Articles();
        List<Article> articleList = getArticleList(param, map);
        articles.setItem(articleList);
        newsMessage.setArticles(articles);
        newsMessage.setArticleCount(articleList.size());
    }
    public abstract List<Article> getArticleList(String param, Map<String, String> map);
    public BaseMessage messageFactory(){
        return new NewsMessage();
    }

    public Map<String,Class> getClassMap(){
        return classMap;
    }

}
