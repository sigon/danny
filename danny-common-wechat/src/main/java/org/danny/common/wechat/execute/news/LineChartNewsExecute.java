package org.danny.common.wechat.execute.news;

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
 * Date: 14-8-19
 * Time: 下午11:43
 * To change this template use File | Settings | File Templates.
 */
@Service("lineChartNewsExecute")
public class LineChartNewsExecute extends NewsExecute {
    @Resource(name = "webMap")
    private Map<String, String> webMap;
    @Override
    public List<Article> getArticleList(String param, Map<String, String> map) {
        List<Article> list = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("查看汇率");
        article.setDescription("点击阅读全文查看折线图");
        article.setPicUrl(webMap.get("domainPath") + "images/chart.jpg");
        article.setUrl(webMap.get("domainPath") + "exchange/lineChart/" + param + ".action");
        list.add(article);
        return list;
    }
}
