package org.danny.common.forex.olsendata;

import org.apache.commons.lang.StringUtils;
import org.danny.common.forex.ForexFactory;
import org.danny.common.forex.ForexItem;
import org.danny.common.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: sigon
 * Time: 15-1-26 下午2:14
 * Project: danny
 */
public class OlsenForexFactory implements ForexFactory {
    private String filePath;
    @Override
    public List<ForexItem> execute(List<String> currency) throws Exception {
        List<ForexItem> list = new ArrayList<ForexItem>();
        Map<String, ForexItem> map = parseFile();
        for(String str:currency){
            list.add(map.get(str));
        }
        return list;
    }
    private Map<String, ForexItem> parseFile() throws Exception{
        String str = FileUtil.readFile(new File(filePath));
        if(StringUtils.isBlank(str)){
            return null;
        }
        String[] strArr = str.split("\n");
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy hh:mm:ss");
        Map<String, ForexItem> map = new HashMap<String, ForexItem>();
        for(String s:strArr){
            String[] forArr = s.split(",");
            ForexItem item = new ForexItem();
            item.setSymbol(forArr[0]);
            Date time = sdf.parse(forArr[1] + " " + forArr[2]);
            item.setModifyDate(time);
            Double rate = Double.parseDouble(forArr[3]) * 100000;
            Integer intRate = rate.intValue();
            item.setXhBuy(intRate);
            item.setXhSell(intRate);
            map.put(forArr[0], item);
        }
        return map;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public static void main(String[] args) throws Exception{
        OlsenForexFactory factory = new OlsenForexFactory();
        factory.setFilePath("/Users/sigon/Downloads/last");
        List<ForexItem> list = factory.execute(Arrays.asList("AUDHKD", "AUDUSD", "NZDHKD"));
        for(ForexItem item: list){
            System.out.print(item.getSymbol());
            System.out.print(" ");
            System.out.print(item.getXhBuy());
            System.out.print(" ");
            System.out.print(item.getXhSell());
            System.out.print(" ");
            System.out.println(item.getModifyDate());
        }
    }
}
