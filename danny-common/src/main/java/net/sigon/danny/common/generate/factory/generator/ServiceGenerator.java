package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Bean;
import net.sigon.danny.common.generate.bean.BeanField;
import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.factory.BaseGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午11:40
 * To change this template use File | Settings | File Templates.
 */
public class ServiceGenerator extends BaseGenerator{

    private String template;
    private String staticPath;
    @Override
    public String getTemplate() {
        return "/java/service.ftl";
    }

    @Override
    public String getStaticPath() {

        return staticPath;
    }

    @Override
    public void trans(Map<String, Object> map) {
        Generate generate = (Generate)map.get("generate");
        Bean bean = (Bean)map.get("bean");
        for(BeanField beanField : bean.getFields()){
            //处理下bean里面的field的name，弄一个大写开头的出来
            beanField.setNameUpper(StringUtils.capitalize(beanField.getName()));
        }

        // todo 给staticPath赋值，组织数据放到map中
        staticPath = generate.getServicePath() + "/" + generate.getServicePackage().replaceAll("\\.", "/") + "/" + bean.getTable() + "/" + StringUtils.capitalize(bean.getTable()) + "Service.java";

        //首字母小写的domain名
        map.put("packageName",bean.getTable());
        //首字母开头的domain名
        map.put("beanName",StringUtils.capitalize(bean.getTable()));
        //类开发负责人
        map.put("author","石光");
        //类生成时间
        map.put("date",new Date());

    }
}
