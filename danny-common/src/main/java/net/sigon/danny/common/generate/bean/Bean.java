package net.sigon.danny.common.generate.bean;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午7:36
 * To change this template use File | Settings | File Templates.
 */
public class Bean {
    private String table;
    private String beanPackage;
    private String primaryKey;
    private List<BeanField> fields;
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
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getBeanPackage() {
        return beanPackage;
    }

    public void setBeanPackage(String beanPackage) {
        this.beanPackage = beanPackage;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<BeanField> getFields() {
        return fields;
    }

    public void setFields(List<BeanField> fields) {
        this.fields = fields;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
