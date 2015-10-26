package org.danny.common.wechat.execute.impl;

import org.danny.common.util.FileUtil;
import org.danny.common.wechat.execute.base.TextExecute;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-7-18
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */
@Service("fileTextExecute")
public class FileTextExecute extends TextExecute {
    private static final String FILE_TEMPLATE = "txt/";

    private Map<String , String> cache = new HashMap<String, String>();
    @Override
    public String getContent(String param, Map<String, String> map) {
        String cacheValue = cache.get(param);
        if(cacheValue != null){

            return cacheValue;
        }
        try {
            File contentFile = new ClassPathResource(FILE_TEMPLATE + param).getFile();
            String content = FileUtil.readFile(contentFile);
            cache.put(param, content);
            return content;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return param;
    }

    public static void main(String[] args){
        System.out.println(new FileTextExecute().getContent("index.txt", null));
    }
}
