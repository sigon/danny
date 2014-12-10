package net.sigon.danny.common.generate.bean;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class Generate {
    private String id;
    private String ftlpath;
    private String servicePath;
    private String servicePackage;
    private String controllerPath;
    private String controllerPackage;
    private String mapperPackage;
    private Bean bean;
    private List<Module> modules;

    public Map<String, Module> getModuleMap(){
        if(CollectionUtils.isEmpty(modules)){
            return null;
        }
        Map<String, Module> map = new HashMap<String, Module>();
        for(int i = 0 ; i < modules.size() ; i ++){
            map.put(modules.get(i).getType(), modules.get(i));
        }
        return map;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFtlpath() {
        return ftlpath;
    }

    public void setFtlpath(String ftlpath) {
        this.ftlpath = ftlpath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
