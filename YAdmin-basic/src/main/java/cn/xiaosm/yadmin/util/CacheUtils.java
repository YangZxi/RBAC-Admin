package cn.xiaosm.yadmin.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
public class CacheUtils {

    private static Map<String, Object> map = new ConcurrentHashMap<>();

    public static void saveObject(String key, Object o) {
        map.put(key, o);
    }

    public static Object getObject(String key) {
        return ObjectUtil.clone(map.get(key));
    }

    public static<T> Object getObject(String key, Class clazz) {
        if (map.containsKey(key)) {
            // return new ObjectMapper().readValue(JSONUtil.toJsonStr(map.get(key)), clazz);
            return ObjectUtil.cloneByStream(map.get(key));
        } else {

        }
        return null;
    }

    public static void removeObject(String key) {
        map.remove(key);
    }

}