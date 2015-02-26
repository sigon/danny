package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Bean;
import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.factory.BaseGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-18
 * Time: 下午8:41
 * To change this template use File | Settings | File Templates.
 */
public class ParamGenerator extends BaseGenerator{
    private String staticPath;
    @Override
    public String getTemplate() {
        return "/java/param.ftl";
    }

    @Override
    public String getStaticPath() {
        return staticPath;
    }

    @Override
    public void trans(Map<String, Object> map) {
        Generate generate = (Generate)map.get("generate");
        Bean bean = (Bean)map.get("bean");
        staticPath = generate.getParamPath() + "/" + generate.getParamPackage().replaceAll("\\.", "/") + "/" + StringUtils.capitalize(bean.getTable()) + "Param.java";
        //类生成时间
        map.put("date",new Date());
    }
}
