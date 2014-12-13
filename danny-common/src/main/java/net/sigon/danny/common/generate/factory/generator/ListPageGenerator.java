package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.bean.Module;
import net.sigon.danny.common.generate.factory.BaseGenerator;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-12
 * Time: 下午9:36
 * To change this template use File | Settings | File Templates.
 */
public class ListPageGenerator extends BaseGenerator{
    private String staticPath;
    @Override
    public String getTemplate() {
        return "/page/list.ftl";
    }

    @Override
    public String getStaticPath() {
        return staticPath;
    }

    @Override
    public void trans(Map<String, Object> map) {
        Generate generate = (Generate)map.get("generate");
        Module module = (Module)map.get("module");
        staticPath = generate.getFtlpath() + module.getTemplate();


    }
}
