package net.sigon.danny.common.test;

import com.google.gson.Gson;
import net.sigon.danny.common.util.FileUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * Author: sigon
 * Time: 15-1-20 下午3:46
 * Project: danny
 */
public class AddressTest {


    public static void main(String[] args){
        //newZealandAddress();
        chinaAddress();
    }
    public static void newZealandAddress(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/sigon/Downloads/lds-nz-street-address-electoral-CSV (1)/nz-street-address-electoral.csv"),"UTF-8"));
            Set<String> set = new TreeSet<String>();
            String s;
            while ((s = br.readLine()) != null) {
                String [] sss = s.split(",");
                set.add(sss[10] + "--" + sss[9]);
                //System.out.println(sss[9] + "-" + sss[10]);
            }
            br.close();
            for(String str:set){
                System.out.print("{txt:\"");
                System.out.print("New Zealand--");
                System.out.print(str);
                System.out.print("\"},");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void chinaAddress(){
        String json = FileUtil.readFile(new File("/Users/sigon/workspace/danny/danny-common/target/classes/address.json"));
        Gson gson = new Gson();
        Address a = gson.fromJson(json.replace("\\", ""), Address.class);
        String preffix = "中国";
        //iterator(preffix, a.getList(), 1);
        List<String> provinceList = new ArrayList<String>();
        List<City> cityList = new ArrayList<City>();
        //list(a.getList());

        iterator(provinceList, cityList, null, a.getList(), 2);
//        System.out.print("[");
//        for(String p:provinceList){
//            System.out.print("\"" + p + "\", ");
//        }
//        System.out.println("]");
        System.out.println(gson.toJson(provinceList));
        System.out.println(gson.toJson(cityList));
    }


    public static void iterator(List<String> provinceList, List<City> cityList, Integer provinceIndex, List<AddressItem> itemList, Integer level){
        for(AddressItem item : itemList) {
            if("请选择".equals(item.getName()) || "海外".equals(item.getName()) || "其他".equals(item.getName())){
                continue;
            }
            if (level == 2) {
                provinceList.add(item.getName());
                if (CollectionUtils.isNotEmpty(item.getSub())) {
                    Integer index = provinceList.indexOf(item.getName());
                    iterator(provinceList, cityList, index, item.getSub(), level + 1);
                }
            } else if (level == 3) {
                City city = new City();
                city.setC(item.getName());
                city.setP(provinceIndex);
                cityList.add(city);
            }
        }

    }
    public static void list(List<AddressItem> list){

        System.out.print("[");
        for(AddressItem item:list){
            if("请选择".equals(item.getName()) || "海外".equals(item.getName()) || "其他".equals(item.getName())){
                continue;
            }
            System.out.print("\"" + item.getName() + "\",");
        }
        System.out.print("]");
    }
    public static void iterator(String preffix, List<AddressItem> list, Integer level){
        for(AddressItem item:list){
//            if(item.getType() != null && item.getType() != 0){
//                continue;
//            }
            if("请选择".equals(item.getName()) || "海外".equals(item.getName()) || "其他".equals(item.getName())){
                continue;
            }
            if(CollectionUtils.isNotEmpty(item.getSub()) && level < 3 && (item.getType() == null || item.getType() != 0)){
                iterator(preffix + "--" + item.getName(), item.getSub(), level + 1);
                continue;
            }
            //System.out.println();
            System.out.print("{txt:\"");
            System.out.print(preffix + "--" + item.getName() + "\"},");
        }
    }
}
