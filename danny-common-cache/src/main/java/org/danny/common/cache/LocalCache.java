package org.danny.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-9-19
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public class LocalCache {
    /**
     * 默认缓存时间
     */
    private static final Long cacheTime = 1000*60*60*24L;
    /**
     * 单例map
     */
    private static Map<String, Cache> map = new HashMap<String, Cache>();

    public static void put(String key, String value, Long time){
        map.put(key, new Cache(value, time));
    }
    public static void put(String key, String value){
        put(key, value, cacheTime);
    }
    public static String get(String key){
        Cache cache = map.get(key);
        if(cache == null){
            return null;
        }
        if(cache.expired > System.currentTimeMillis()){
            return cache.value;
        }
        return null;
    }

    static class Cache{
        String value;
        Long expired;
        Cache(String value, Long time){
            this.value = value;
            this.expired = System.currentTimeMillis() + time;
        }
        String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }

        Long getExpired() {
            return expired;
        }

        void setExpired(Long expired) {
            this.expired = expired;
        }
    }
}