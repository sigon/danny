package org.danny.common.wechat.execute.news;

import org.danny.common.wechat.domain.resp.Article;
import org.danny.common.wechat.execute.base.NewsExecute;
import org.springframework.stereotype.Service;

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
@Service("singleNewsExecute")
public class SingleNewsExecute extends NewsExecute {
    @Override
    public List<Article> getArticleList(String param, Map<String, String> map) {
        List<Article> list = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("标题");
        article.setDescription("描述\r\n描述\r\n描述\r\n描述\r\n描述\n描述\n描述\n描述\n");
        article.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/c9fcc3cec3fdfc03a4824223d63f8794a5c226b6.jpg");
        article.setUrl("http://baike.baidu.com/view/281568.htm");
        list.add(article);
        Article a2 = new Article();
        a2.setTitle("板蓝根");
        a2.setDescription("包治百病的板蓝根");
        a2.setPicUrl("http://imgsrc.baidu.com/forum/w%3D580/sign=94576397a3ec08fa260013af69ee3d4d/21d3850f4bfbfbedc830f1577bf0f736afc31f2d.jpg");
        a2.setUrl("http://tieba.baidu.com/p/3241269476");
        list.add(a2);
        return list;
    }
}
