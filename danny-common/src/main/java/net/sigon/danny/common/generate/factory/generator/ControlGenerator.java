package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Bean;
import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.factory.BaseGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yf-liulei
 * Date: 14-12-18
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class ControlGenerator extends BaseGenerator {
    private String staticPath;
    @Override
    public String getTemplate() {
        return "/java/controller.ftl";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStaticPath() {
        return staticPath;
    }

    @Override
    public void trans(Map<String, Object> map) {
        Generate generate = (Generate)map.get("generate");
        Bean bean = (Bean)map.get("bean");
        staticPath = generate.getControllerPath() + "/" + generate.getControllerPackage().replaceAll("\\.", "/") + "/" + bean.getTable() + "/" + StringUtils.capitalize(bean.getTable()) + "Controller.java";

        //首字母小写的domain名
        map.put("packageName",bean.getTable());
        //首字母开头的domain名
        map.put("beanName", StringUtils.capitalize(bean.getTable()));

        //类开发负责人
        map.put("author","sigon");
        //类生成时间
        map.put("date",new Date());
    }
}
