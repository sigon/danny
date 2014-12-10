package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Configuration;
import net.sigon.danny.common.generate.bean.Generate;
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

    private Configuration configuration;
    private Generate generate;
    public ServiceGenerator(Configuration configuration, Generate generate){
        this.configuration = configuration;
        this.generate = generate;
    }
    @Override
    public String getTemplate() {
        return "/java/service.ftl";
    }

    @Override
    public String getStaticPath() {
        String servicePath = generate.getServicePath();
        String servicePackage = generate.getServicePackage();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void trans(Map<String, Object> map) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
