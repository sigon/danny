package net.sigon.codenote.common.util;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-21
 * Time: 下午8:27
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {
    public static String readFile(File file){
        try {
            StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String s;
            while ((s = br.readLine()) != null) {
                result.append(s).append("\n");
            }
            br.close();
            br = null;
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
