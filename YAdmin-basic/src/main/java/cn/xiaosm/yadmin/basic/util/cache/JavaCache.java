package cn.xiaosm.yadmin.basic.util.cache;

import cn.hutool.core.util.ObjectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Young
 * @create 2021/5/8
 * @since 1.0.0
 */
public class JavaCache implements CacheHandler{

    private static Map<String, Object> map = null;

    public JavaCache() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void set(String key, Object value, long exp) {
        map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return ObjectUtil.clone(map.get(key));
    }

    @Override
    public void delete(String key) {
        map.remove(key);
    }
}