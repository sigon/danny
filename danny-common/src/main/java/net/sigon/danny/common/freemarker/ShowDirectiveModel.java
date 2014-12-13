package net.sigon.danny.common.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-12
 * Time: 下午11:43
 * To change this template use File | Settings | File Templates.
 */
public class ShowDirectiveModel implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        if(body != null){
            out.write("${");
            body.render(env.getOut());
            out.write("}");
        }
    }
}
