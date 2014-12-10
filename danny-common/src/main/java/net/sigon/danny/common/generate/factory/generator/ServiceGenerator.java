package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.factory.BaseGenerator;

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
        // todo 给staticPath赋值，组织数据放到map中
    }
}
