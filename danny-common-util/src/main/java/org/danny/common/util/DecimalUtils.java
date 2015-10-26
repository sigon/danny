package org.danny.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-9-18
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public class DecimalUtils {
    private static String formatStr = "0.0000";
    private static DecimalFormat format = new DecimalFormat(formatStr);

    public static String formatDoublePoint4(String str){
        if(StringUtils.isBlank(str)){
            return "0.0000";
        }
        Double d = Double.parseDouble(str);
        return format.format(d);
    }

}
