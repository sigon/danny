package org.danny.common.util;

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

    public static int writeFile(String content, String path){
        try {
            FileWriter fw = new FileWriter(path);
            StringReader reader = new StringReader(content);
            char[] chars = new char[1024];
            int len = 0;
            while ((len = reader.read(chars)) != -1) {
                String strRead = new String(chars, 0, len);
                //System.out.println(strRead);
                fw.write(strRead);
                fw.flush();
            }

            //fw.write(content);
            fw.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
