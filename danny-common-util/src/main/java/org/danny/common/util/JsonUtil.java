package org.danny.common.util;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.Map;

/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-4-4
 *         Time: 下午5:26
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public class JsonUtil {
    /**
     * 将json字符串转换对象
     *
     * @param json json字符串
     * @param c         class 对象
     * @return
     * @throws Exception
     */
    public static Object jsonToObject(String json, Class c, Map<String ,Class> map) throws Exception{
        JSONObject object = JSONObject.fromObject(json);
        return JSONObject.toBean(object, c, map);
    }
    public static Object jsonToObject(String paramJson, Class c) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(paramJson);
        Object object = JSONObject.toBean(jsonObject, c);
        return object;
    }

    public static Object[] jsonToObjectArray(String paramJson, Class c) throws Exception {
        JSONArray jsonArr = JSONArray.fromObject(paramJson);

        Object[] obj = new Object[jsonArr.size()];
        for (int j = 0; j < jsonArr.size(); j++) {
            JSONObject jsonObject = jsonArr.getJSONObject(j);
            obj[j] = JSONObject.toBean(jsonObject, c);
        }
        return obj;
    }

    public static String ObjectToRpcJson(Object object, Map<String, Class> classMap){
        //首先构造过滤null的条件
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if(name.equals("code") || name.equals("msg")){
                    return true;
                }
                if (value == null) {// 这里是过滤的关键
                    return true;
                }
                return false;
            }
        });

        //注册日期类型的转换格式
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(DateUtil.DATE_FORMATE));

        if(classMap != null){
            jsonConfig.setClassMap(classMap);
        }
        JSONObject jo = JSONObject.fromObject(object, jsonConfig);
        return jo.toString();
    }
    public static String ObjectToJson(Object object, Map<String, Class> classMap) throws Exception{
        //首先构造过滤null的条件
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {// 这里是过滤的关键
                    return true;
                }
                return false;
            }
        });

        //注册日期类型的转换格式
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(DateUtil.DATE_FORMATE));

        if(classMap != null){
            jsonConfig.setClassMap(classMap);
        }
        JSONObject jo = JSONObject.fromObject(object, jsonConfig);
        return jo.toString();
    }
    /**
     * 将对象转换成字符串
     *
     * @param object bean 对象
     * @return
     * @throws Exception
     */
    public static String ObjectToJson(Object object) throws Exception {
        return ObjectToJson(object, null);
    }

    public static Object fromJson(String json, Type type){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(json, type);
    }
}
