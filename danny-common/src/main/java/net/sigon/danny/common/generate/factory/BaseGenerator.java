package net.sigon.danny.common.generate.factory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午10:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseGenerator {

    private static Configuration cfg;

    private Configuration getConfiguration() throws IOException {
        if(cfg != null){
            return cfg;
        }
        cfg = new Configuration();
        // 指定模板文件从何处加载的数据源,这里设置成一个文件目录。
        cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/danny-common/target/classes/templates"));
        // 指定模板如何检索数据模型,这是一个高级的主题了...
        // 但先可以这么来用:
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }
    public abstract String getTemplate();
    public abstract String getStaticPath();
    public abstract void trans(Map<String, Object> map);

    public String execute(net.sigon.danny.common.generate.bean.Configuration configuration) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", configuration);
        trans(map);
        int result = build(map);

        return null;
    }
    public int build(Map<String, Object> model) throws IOException {


        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        Writer writer = null;
        try {
            freemarker.template.Template template = getConfiguration().getTemplate(getTemplate());
            File staticFile = new File(getStaticPath());
            if(staticFile.exists()){
                System.out.println(getStaticPath() + " will overwrite.");
            }
            File staticDirectory = staticFile.getParentFile();
            if (!staticDirectory.exists()) {
                staticDirectory.mkdirs();
            }
            fileOutputStream = new FileOutputStream(staticFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            writer = new BufferedWriter(outputStreamWriter);
            template.process(model, writer);
            writer.flush();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return 0;
    }
}
