package net.sigon.danny.common.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Sigon
 * Date: 13-9-23
 * Time: ����9:52
 * To change this template use File | Settings | File Templates.
 */
public class ReflectUtils {
    public static String[] parseArray(Object o, Set<String> set){
        String [] sa = new String[set.size()];
        Field[] fields = o.getClass().getDeclaredFields();
        for(String f : set){

        }
        return sa;
    }

    public static List parseArray(List<String> fields, List<String[]> dataList, Class clazz){
        List list = new ArrayList();
        for(String [] data: dataList){
            try {
                Object o = clazz.newInstance();
                for(Integer i = 0 ; i < fields.size(); i ++){
                	if(StringUtils.isBlank(fields.get(i))){
                		continue;
                	}
                    Field field = clazz.getDeclaredField(fields.get(i));
                    field.setAccessible(true);
                    if(field != null && StringUtils.isNotBlank(data[i])){
                        Object ff = data[i];
                        if(field.getType().equals(Date.class)){
                            if(data[i].length() == 10){
                                ff = DateUtil.getStrToDate(DateUtil.DATE_FORMATE, data[i]);
                            }else{
                                ff = DateUtil.getStrToDate(DateUtil.DATE_FORMAT_1, data[i]);
                            }
                        }else if (field.getType().equals(Long.class)){
                            ff = Long.parseLong(data[i]);
                        }else if (field.getType().equals(Integer.class)){
                            ff = Integer.parseInt(data[i]);
                        }else if (field.getType().equals(Double.class)){
                            ff = Double.parseDouble(data[i]);
                        }
                        field.set(o, ff);
                    }
                }
                list.add(o);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return list;
    }
}
