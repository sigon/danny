package org.danny.common.wechat.execute.news;

import org.apache.commons.lang.StringUtils;
import org.danny.common.util.JsonUtil;
import org.danny.common.wechat.domain.resp.Article;
import org.danny.common.wechat.execute.base.NewsExecute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-26
 * Time: 下午8:59
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultNewsExecute")
public class DefaultNewsExecute extends NewsExecute {
    @Resource(name = "webMap")
    private Map<String, String> webMap;
    @Override
    public List<Article> getArticleList(String param, Map<String, String> map) {
        try {
            Article article = (Article) JsonUtil.jsonToObject(param, Article.class);
            if(StringUtils.isNotBlank(article.getPicUrl()) && !article.getPicUrl().startsWith("http")){
                article.setPicUrl(webMap.get("domainPath") + article.getPicUrl());
            }
            if(StringUtils.isNotBlank(article.getUrl()) && !article.getUrl().startsWith("http")){
                article.setUrl(webMap.get("domainPath") + article.getUrl());
            }
//            Map<String, String> articleMap = JacksonJsonUtil.readJsonBeanInMap(param);
//            Article article = new Article();
//            article.setTitle(articleMap.get("Title"));
//            article.setDescription(articleMap.get("Description"));
//            article.setUrl(articleMap.get("Url"));
//            article.setPicUrl(articleMap.get("PicUrl"));
            List<Article> list = new ArrayList<Article>();
            list.add(article);
            return list;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
