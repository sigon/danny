package net.sigon.danny.common.generate.factory;

import net.sigon.danny.common.generate.bean.Configuration;
import net.sigon.danny.common.util.FileUtil;
import net.sigon.danny.common.util.JacksonJsonUtil;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationFactory {

    public static Configuration create(String filePath)throws Exception{
        String json = FileUtil.readFile(new File(filePath));
        Configuration configuration = (Configuration)JacksonJsonUtil.jsonToBean(json, Configuration.class);
        return configuration;
    }

    public static void main(String [] args){
        try {
            //Configuration configuration = create("/Users/sigon/workspace/danny/danny-common/target/classes/generate.json");
            System.out.println(System.getProperty("user.dir"));

            File directory = new File("");//设定为当前文件夹
                System.out.println(directory.getCanonicalPath());//获取标准的路径
                System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
