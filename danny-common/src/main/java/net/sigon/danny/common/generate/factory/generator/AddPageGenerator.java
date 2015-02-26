package net.sigon.danny.common.generate.factory.generator;

import net.sigon.danny.common.generate.bean.Bean;
import net.sigon.danny.common.generate.bean.BeanField;
import net.sigon.danny.common.generate.bean.Generate;
import net.sigon.danny.common.generate.bean.Module;
import net.sigon.danny.common.generate.factory.BaseGenerator;
import net.sigon.danny.common.util.ObjectFactory;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-15
 * Time: 下午9:23
 * To change this template use File | Settings | File Templates.
 */
public class AddPageGenerator extends BaseGenerator {

    private String staticPath;
    private String template;

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public String getStaticPath() {
        return staticPath;
    }

    @Override
    public void trans(Map<String, Object> map) {
        Bean bean = (Bean)map.get("bean");
        Generate generate = (Generate)map.get("generate");
        Module module = (Module)map.get("module");

        staticPath = generate.getFtlpath() + "/" + bean.getModuleName() + "/add.ftl";
        template = String.format("/page/%s/add.ftl", generate.getProject());
        String className = bean.getBeanPackage() + "." + StringUtils.capitalize(bean.getTable());

        Map<String, BeanField> fieldMap = bean.getFieldMap();
        List<BeanField> fieldList = new ArrayList<BeanField>();
        try {
            Class clazz = ObjectFactory.externalClassForName(className);
            Field[] fields = clazz.getDeclaredFields();
            for(Field f:fields){
                if(module.getIgnoreFields().indexOf(f.getName()) != -1){
                    continue;
                }
                if(bean.getPrimaryKey().equals(f.getName())){
                    continue;
                }
                if(fieldMap != null && fieldMap.get(f.getName()) != null){
                    fieldList.add(fieldMap.get(f.getName()));
                    continue;
                }
                BeanField field = new BeanField();
                field.setName(f.getName());
                fieldList.add(field);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        map.put("fields", fieldList);
    }
}
