package net.sigon.danny.common.generate.factory;

import net.sigon.danny.common.generate.bean.Bean;
import net.sigon.danny.common.generate.bean.Configuration;
import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.bean.Module;
import net.sigon.danny.common.generate.factory.generator.AddPageGenerator;
import net.sigon.danny.common.generate.factory.generator.ListPageGenerator;
import net.sigon.danny.common.generate.factory.generator.ServiceGenerator;
import net.sigon.danny.common.generate.factory.generator.ServiceImplGenerator;
import net.sigon.danny.common.util.ClassloaderUtility;
import net.sigon.danny.common.util.ObjectFactory;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
    public class CoreFactory {

    private BaseGenerator serviceGenerator;
    private BaseGenerator serviceImplGenerator;
    private BaseGenerator controllerGenerator;
    private Map<String, BaseGenerator> moduleGeneratorMap;
    public Boolean execute(Configuration config) throws IOException {
        init(config);
        for(Generate generate:config.getGenerates()){
            for(Bean bean:generate.getBeans()){
                serviceGenerator.execute(config, generate, bean, null);
                serviceImplGenerator.execute(config, generate, bean, null);
//                controllerGenerator.execute(config, generate, bean, null);
                for(Module module : bean.getModules()){
                    BaseGenerator moduleGenerator = moduleGeneratorMap.get(module.getType());
                    if(moduleGenerator != null){
                        moduleGenerator.execute(config, generate, bean, module);
                    }
                }
            }
        }

        return true;
    }
    public void init(Configuration config){
        if(config != null && CollectionUtils.isEmpty(config.getClassPaths())){
            return;
        }
        ObjectFactory.addExternalClassLoader(ClassloaderUtility.getCustomClassloader(config.getClassPaths()));

        //todo 初始化serviceGenerator,controllerGenerator和moduleGeneratorMap
        serviceGenerator = new ServiceGenerator();
        serviceImplGenerator = new ServiceImplGenerator();
        moduleGeneratorMap = new HashMap<String, BaseGenerator>();
        moduleGeneratorMap.put("list", new ListPageGenerator());
        moduleGeneratorMap.put("add", new AddPageGenerator());

//
//        try {
//            conn = ConnectionFactory.getInstance().getConnection(config.getJdbcConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }
    public static void main(String [] args){
        CoreFactory coreFactory = new CoreFactory();
        try {
            coreFactory.execute(ConfigurationFactory.create("generate.json"));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
