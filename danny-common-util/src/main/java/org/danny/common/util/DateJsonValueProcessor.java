package org.danny.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.util.Date;

/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-4-10
 *         Time: 下午4:57
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String dateFormat;
    public DateJsonValueProcessor(){
        dateFormat = DateUtil.DATE_FORMATE;
    }
    public DateJsonValueProcessor(String dateFormat){
        this.dateFormat = dateFormat;
    }
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value instanceof Date[])
        {
            Date[] dates = (Date[]) value;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++)
            {
                obj[i] = DateUtil.date2String(dates[i], dateFormat);
            }
        }
        return obj;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value instanceof Date)
        {
            String str = DateUtil.date2String((Date) value, dateFormat);
            return str;
        }
        return value;
    }
}
